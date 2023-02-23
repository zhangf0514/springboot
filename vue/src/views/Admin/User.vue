<template>
<div>
  <div style="margin: 10px 0">
    <el-input style="width: 200px" suffix-icon="el-icon-search" placeholder="请输入名称" v-model="username"></el-input>
    <el-input style="width: 200px" suffix-icon="el-icon-message" placeholder="请输入邮箱" v-model="email" class="ml-5"></el-input>
    <el-input style="width: 200px" suffix-icon="el-icon-position" placeholder="请输入地址" v-model="address" class="ml-5"></el-input>
    <el-button class="ml-5" type="primary" @click="load">搜索</el-button>
    <el-button type="warning" @click="reset">重置</el-button>
  </div>
  <div style="margin: 10px 0">
    <el-upload action="http://localhost:9090/user/import" style="display: inline-block" :show-file-list="false" accept=".xlsx" :on-success="handleExcelImportSucess">
        <el-button class="ml-5" type="primary">导入<i class="el-icon-bottom"></i></el-button>
    </el-upload>
    <el-button class="ml-5" type="primary" @click="exp">导出<i class="el-icon-top"></i></el-button>
  </div>
  <el-table :data="tableData" border stripe :header-cell-class-name="headerBg"  @selection-change="handleSelectionChange">
    <el-table-column type="selection" width="55"></el-table-column>
    <el-table-column prop="id" label="ID" width="80">
    </el-table-column>
    <el-table-column prop="username" label="用户名" width="140">
    </el-table-column>
    <el-table-column prop="nickname" label="昵称" width="120">
    </el-table-column>
    <el-table-column prop="phone" label="电话">
    </el-table-column>
    <el-table-column prop="email" label="邮箱">
    </el-table-column>
    <el-table-column prop="address" label="地址">
    </el-table-column>
  </el-table>
  <div style="padding: 10px 0">
    <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="pageNum"
        :page-sizes="[2, 5, 10, 20]"
        :page-size="pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total">
    </el-pagination>
  </div>

  <el-dialog title="用户信息" :visible.sync="dialogFormVisible" width="30%" >
    <el-form label-width="80px" size="small">
      <el-form-item label="用户名">
        <el-input v-model="form.username" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="昵称">
        <el-input v-model="form.nickname" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="邮箱">
        <el-input v-model="form.email" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="电话">
        <el-input v-model="form.phone" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="地址">
        <el-input v-model="form.address" autocomplete="off"></el-input>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button @click="dialogFormVisible = false">取 消</el-button>
      <el-button type="primary" @click="save">确 定</el-button>
    </div>
  </el-dialog>
</div>
</template>

<script>
export default {
  name: "User",
  data(){
    return{
      tableData:[],
      total:0,
      pageNum:1,
      pageSize:10,
      username:'',
      email:'',
      address:'',
      form:{},
      dialogFormVisible:false,
      multipleSelection:[],
      headerBg:"headerBg",
    }
  },
  created() {
    this.load()
  },
  methods:{
    load(){
      this.request.get("/user/page",{
        params:{
          pageNum:this.pageNum,
          pageSize:this.pageSize,
          username:this.username,
          email:this.email,
          address:this.address
        }
      })
          .then(res => {
            this.tableData=res.data.records
            this.total=res.data.total
          })
    },
    reset(){
      this.username=""
      this.email=""
      this.address=""
      this.load()
    },
    save(){
      this.request.post("/user/",this.form).then(res=>{
        if(res.code==="200"){
              this.$message.success("保存成功")
              this.dialogFormVisible=false
              this.load()
            }else {
              this.$message.error("保存失败")
            }
          }
      )
    },
    handleEdit(row){
      this.form=row
      this.dialogFormVisible=true
    },
    handleAdd(){
      this.dialogFormVisible=true
      this.form={}
    },
    handleDel(id){
      this.request.delete("/user/"+id).then(res=>{
        if(res.code==="200"){
          this.$message.success("删除成功")
          this.load()
        }else {
          this.$message.error("删除失败")
        }
      })
    },
    delBatch(){
      let ids=this.multipleSelection.map(v => v.id)
      this.request.post("/user/del/batch",ids).then(res=>{
        if(res.code==="200"){
          this.$message.success("批量删除成功")
          this.load()
        }else {
          this.$message.error("批量删除失败")
        }
      })
    },
    handleSelectionChange(val){
      this.multipleSelection=val
    },
    handleSizeChange(pageSize){
      this.pageSize=pageSize
      this.load()
    },
    handleCurrentChange(pageNum){
      this.pageNum=pageNum
      this.load()
    },
    exp(){
      window.open("http://localhost:9090/user/export")
    },
    handleExcelImportSucess(){
      this.$message.success("文件导入成功");
      this.load()
    },

  }
}
</script>

<style>
.headerBg{
  background: #eee!important;
}
</style>