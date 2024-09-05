<script setup>
import { ref } from 'vue'
const research = ref('')
const select = ref('')
import { Search } from "@element-plus/icons-vue";
</script>
<template>
  <div class="index">
    <div>
      <el-row>
        <el-col :span="12">
          <el-input placeholder="请输入内容" v-model="book_name" class="handle-select handle-input mr10">
            <template #prepend>
              <el-select v-model="select" slot="prepend" placeholder="请选择" @change="handleSearch">
                <el-option label="编号" value="1"></el-option>
                <el-option label="图书名" value="2"></el-option>
                <el-option label="作者" value="3"></el-option>
                <el-option label="类型" value="4"></el-option>
              </el-select>
            </template>
            <template #append>
              <el-button :icon="Search" slot="append" @click="handleSearchButton(select)"></el-button>
            </template>
          </el-input>
        </el-col>
        <el-col span="4">
          <el-button :disabled='adddisabled' type="primary" @click="addBookVisible = true">增加图书</el-button>
          <el-dialog title="添加书籍" v-model="addBookVisible" width="80%">
            <el-form ref="form" :model="form" label-width="80px">
              <el-form-item label="书籍编号:">
                <el-input v-model="form.isbn"></el-input>
              </el-form-item>
              <el-form-item label="书名:">
                <el-input v-model="form.name"></el-input>
              </el-form-item>
              <el-form-item label="作者:">
                <el-input v-model="form.author"></el-input>
              </el-form-item>
              <el-form-item label="借阅状态:">
                <el-input v-model="form.status"></el-input>
              </el-form-item>
              <el-form-item label="类型">
                <el-input v-model="form.category"></el-input>
              </el-form-item>
              <el-form-item>
                <el-button type="primary" @click="addBook">立即添加图书</el-button>
                <el-button @click="addBookVisible = false">取消</el-button>
              </el-form-item>
            </el-form>
          </el-dialog>
        </el-col>
      </el-row>

    </div>
    <div style="margin-top: 20px">
      <el-table stripe border :data="tableData" style="width: 100%" :table-layout="'fixed'">
        <el-table-column prop="isbn" label="图书编号" width="150" />
        <el-table-column prop="name" label="图书名称" width="150" />
        <el-table-column prop="author" label="作者" width="150" />
        <el-table-column prop="status" label="借阅状态" :formatter="formatter" width="100" />
        <el-table-column prop="category" label="类型" width="100" />
        <el-table-column prop="rent_date" label="借阅时间" width="180" />
        <el-table-column prop="return_date" label="归还时间" width="180" />
        <el-table-column fixed="right" label="操作">
          <template v-slot="scope" #default>
            <el-button :disabled='updatedisabled' type="warning" @click="updateBook(scope.row)">更改图书</el-button>
            <el-dialog title="更改书籍" v-model="updateBookVisible" width="80%">
              <el-form ref="updateBookForm" :model="updateBookForm" label-width="80px">
                <el-form-item label="书籍编号:">
                  <el-input v-model="updateBookForm.isbn"></el-input>
                </el-form-item>
                <el-form-item label="书名:">
                  <el-input v-model="updateBookForm.name"></el-input>
                </el-form-item>
                <el-form-item label="作者:">
                  <el-input v-model="updateBookForm.author"></el-input>
                </el-form-item>
                <el-form-item label="借阅状态:">
                  <el-input v-model="updateBookForm.status"></el-input>
                </el-form-item>
                <el-form-item label="类型">
                  <el-input v-model="updateBookForm.category"></el-input>
                </el-form-item>
                <el-form-item>
                  <el-button type="primary" @click="onupdateBook">立即更改图书</el-button>
                  <el-button @click="updateBookVisible = false">取消</el-button>
                </el-form-item>
              </el-form>
            </el-dialog>
            <el-popconfirm title="确认要删除该书籍吗?" @confirm="deleteBook(scope.row)">
              <template #reference>
                <el-button :disabled='deletedisabled' style="margin: 20px" type="primary"
                  slot="reference">删除图书</el-button>
              </template>
            </el-popconfirm>
            <el-button style="margin: 20px" type="success"
              @click="rentOrreturn(scope.row.isbn, scope.row.status)">借阅/归还图书</el-button>
            <el-dialog title="借阅书籍" v-model="rentBookVisible" v-slot="scope">
              <el-date-picker v-model="value" type="daterange" start-placeholder="Start date" end-placeholder="End date"
                :default-time="defaultTime" append-to-body="true" value-format="yyyy-MM-dd" />
              <el-button style="margin: 20px" type="primary" slot="reference"
                @click="rentBook(2023 - 0o6 - 28, 2023 - 0o6 - 29, scope.row.isbn)">借阅图书</el-button>
            </el-dialog>

          </template>
        </el-table-column>
      </el-table>


    </div>

  </div>
</template>
<script>
import axios from "axios";
import { ref } from "vue";
import { useStore } from "@/stores";
let select;
let adddisabled, updatedisabled, deletedisabled;
adddisabled = true
updatedisabled = true
deletedisabled = true
const store = useStore()
const value = ref('')
const defaultTime = ref < [Date, Date] > ([
  new Date(2000, 1, 1, 0, 0, 0),
  new Date(2000, 2, 1, 23, 59, 59),
])
export default {
  name: 'IndexPage',
  const: select = 1,
  mounted() {
    this.getBook();
    this.validateuser();
  },
  methods: {
    formatter(row, column) {
      let ret = '未借出';
      if (row.status === false) {
        ret = '未借出'
      }
      else if (row.status === true) {
        ret = '已借出'
      }
      return ret;
    },
    rentOrreturn(isbn, status) {
      if (status === false) {
        this.rentBookVisible = true

        return '借阅'
      }
      else if (status === true) {
        this.returnBook(isbn)
        return '归还'
      }
    },
    rentBook(rent_date, return_date, isbn) {
      axios.get('http://localhost:8080/api/book/rent?rent_date=' + rent_date + '&return_date=' + return_date + '&isbn=' + isbn, {
        rent_date: rent_date,
        return_date: return_date,
        isbn: isbn,
      }).
        then((response) => {
          console.log("借阅图书成功")
          this.rentBookVisible = false
          if (response.data === 0) {
            this.getBook()
          } else {
            this.$message.error(response.data.msg);
          }
        })
    },
    returnBook(isbn) {
      axios.get('http://localhost:8080/api/book/return?isbn=' + isbn, {
        isbn: isbn,
      }).
        then((response) => {
          console.log("归还图书成功")
          if (response.data === 0) {
            this.getBook()
          } else {
            this.$message.error(response.data.msg);
          }
        })
    },
    addBook() {
      axios.post('http://localhost:8080/api/book/add', {
        isbn: this.form.isbn,
        name: this.form.name,
        author: this.form.author,
        status: this.form.status,
        category: this.form.category,
      },
        {
          params: this.form
        })
        .then((response) => {
          console.log("添加图书成功")
          if (response.data === 0) {
            this.addBookVisible = false
            this.getBook()
          } else {
            this.$message.error(response.data.msg);
          }
        })
    },
    deleteBook(row) {
      axios.post('http://localhost:8080/api/book/delete?isbn=' + row.isbn).
        then((response) => {
          console.log(row.isbn)
          console.log("删除图书成功")
          if (response.data === 0) {
            this.updateBookVisible = false
            this.getBook()
          } else {
            this.$message.error(response.data.msg);
          }
        })
    },
    updateBook(book) {
      console.log(book)
      this.updateBookForm = book
      this.updateBookVisible = true
    },
    onupdateBook() {
      axios.post('http://localhost:8080/api/book/update', {
        isbn: this.updateBookForm.isbn,
        name: this.updateBookForm.name,
        author: this.updateBookForm.author,
        status: this.updateBookForm.status,
        category: this.updateBookForm.category,
      },
        {
          params: this.updateBookForm
        })
        .then((response) => {
          console.log("更改图书成功")
          if (response.data === 0) {
            this.updateBookVisible = false
            this.getBook()
          } else {
            this.$message.error(response.data.msg);
          }
        }).catch((error) => {
          console.log("更改图书失败")
        })
    },
    SearchBookByIsbn(isbn) {
      if (isbn === '') {
        this.getBook()
      }
      else
        axios.get('api/book/searchIsbn?isbn=' + isbn)
          .then((response) => {
            console.log(isbn)
            this.tableData = response.data
          })
    },
    SearchBookByName(name) {
      if (name === '') {
        this.getBook()
      }
      else
        axios.get('api/book/searchName?name=' + name)
          .then((response) => {
            console.log(name)
            this.tableData = response.data
          })
    },
    SearchBookByAuthor(author) {
      if (author === '') {
        this.getBook()
      }
      else
        axios.get('api/book/searchAuthor?author=' + author)
          .then((response) => {
            console.log(author)
            this.tableData = response.data
          })
    },
    SearchBookByCategory(category) {
      if (category === '') {
        this.getBook()
      }
      else
        axios.get('api/book/searchCategory?category=' + category)
          .then((response) => {
            console.log(category)
            this.tableData = response.data
          })
    },
    validateuser() {
      if (store.auth.user.username === 'admin') {
        adddisabled = false
        updatedisabled = false
        deletedisabled = false
      }
    },
    getBook() {
      axios.get('http://localhost:8080/api/book/show')
        .then((response) => {
          console.log(response.data)
          this.tableData = response.data
        })
    },
    handleSearch(select) {
      this._select = select
      console.log(this._select)
      return this._select
    },
    handleSearchButton(select) {
      const _this = this;
      const getData = _this.book_name;
      if (select === '1') {
        console.log(select)
        console.log(getData)
        this.SearchBookByIsbn(getData)
      } else if (select === '2') {
        console.log(select)
        this.SearchBookByName(getData)
      } else if (select === '3') {
        console.log(select)
        this.SearchBookByAuthor(getData)
      }
      else if (select === '4') {
        console.log(select)
        this.SearchBookByCategory(getData)
      }
    }
  },

  data() {
    return {
      book_name: '',
      _select: 1,
      select: this._select,
      updateBookForm: {
        isbn: '',
        name: '',
        author: '',
        status: 0,
        category: '',
        rent_date: '',
        return_date: ''
      },
      rentBookVisible: false,
      updateBookVisible: false,
      tableData: [],
      addBookVisible: false,
      form: {
        isbn: '',
        name: '',
        author: '',
        status: 0,
        category: '',
        rent_date: '',
        return_date: ''
      }
    }
  },
}

</script>
<style scoped>
.index {
  width: 100%;
  height: 100vw;
  overflow: hidden;

}
</style>