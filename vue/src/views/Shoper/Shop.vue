<template>
<div>
  <div style="margin: 10px 0">
    <el-input style="width: 200px" suffix-icon="el-icon-search" placeholder="请输入名称" v-model="shopname"></el-input>
    <el-input style="width: 200px" suffix-icon="el-icon-phone" placeholder="请输入电话" v-model="shopphone"></el-input>
    <el-input style="width: 200px" suffix-icon="el-icon-position" placeholder="请输入地址" v-model="shopaddress" class="ml-5"></el-input>
    <el-button class="ml-5" type="primary" @click="load">搜索</el-button>
    <el-button type="warning" @click="reset">重置</el-button>
  </div>
  <div style="margin: 10px 0">
    <el-button class="ml-5" type="primary" @click="handleAdd">新增 <i class="el-icon-circle-plus-outline"></i></el-button>
    <el-popconfirm
        class="ml-5"
        confirm-button-text='确定'
        cancel-button-text='我再想想'
        icon="el-icon-info"
        icon-color="red"
        title="您确定批量删除这些数据吗？"
        @confirm="delBatch"
    >
      <el-button type="danger" slot="reference">批量删除<i class="el-icon-remove-outline"></i></el-button>
    </el-popconfirm>
    </div>
  <el-table :data="tableData" border stripe :header-cell-class-name="headerBg"  @selection-change="handleSelectionChange">
    <el-table-column type="selection" width="55"></el-table-column>
    <el-table-column prop="id" label="ID" width="80">
    </el-table-column>
    <el-table-column prop="shopname" label="店铺名称" width="140">
    </el-table-column>
    <el-table-column prop="shopphone" label="店铺电话" width="120">
    </el-table-column>
    <el-table-column prop="shopaddress" label="店铺地址">
    </el-table-column>
    <el-table-column label="是否启用">
      <template slot-scope="scope">
        <el-switch v-model="scope.row.state" :disabled="!scope.row.enable" active-color="#13ce66" inactive-color="#ccc"  @change="changeState(scope.row)"></el-switch>
      </template>
    </el-table-column>
    <el-table-column label="操作" align="center">
      <template slot-scope="scope">
        <el-popconfirm
            class="ml-5"
            confirm-button-text='确定'
            cancel-button-text='我再想想'
            icon="el-icon-info"
            icon-color="red"
            title="您确定删除吗？"
            @confirm="handleDel(scope.row.id)"
        >
          <el-button type="danger" slot="reference">删除<i class="el-icon-remove-outline"></i></el-button>
        </el-popconfirm>

      </template>
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
      <el-form-item label="店铺名称">
        <el-input v-model="form.shopname" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="店铺电话">
        <el-input v-model="form.shopphone" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="店铺地址">
        <el-input v-model="form.shopaddress" autocomplete="off"></el-input>
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
      shopname:'',
      shopphone:'',
      shopaddress:'',
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
      this.request.get("/shop/page",{
        params:{
          pageNum:this.pageNum,
          pageSize:this.pageSize,
          shopname:this.shopname,
          shopphone:this.shopphone,
          shopaddress:this.shopaddress,
          shopuserid:JSON.parse(localStorage.getItem("user")).id
        }
      })
          .then(res => {
            this.tableData=res.data.records
            if (res.data.records[0]!=null){
            localStorage.setItem("shopUserId",res.data.records[0].shopuserid)
            localStorage.setItem("shopId",res.data.records[0].id)
            }
            this.total=res.data.total
          })
    },
    reset(){
      this.shopname=""
      this.shopphone=""
      this.shopaddress=""
      this.load()
    },
    save(){
      this.form.shopuserid=JSON.parse(localStorage.getItem("user")).id
      this.request.post("/shop/", this.form).then(res=>{
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
      this.request.delete("/shop/"+id).then(res=>{
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
      this.request.post("/shop/del/batch",ids).then(res=>{
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
    handleExcelImportSucess(){
      this.$message.success("文件导入成功");
      this.load()
    },
    changeState(row){
      this.request.get("/shop/updateByIdState",{
        params:{
          id:row.id,
          state:row.state
        }}).then(res=> {
        if (res.code==='200'){
          this.$message.success("操作成功")
          this.tableData.enable=row.enable
          localStorage.setItem("state",row.state)
         window.location.reload()
        }
      })
    },

  }
}
</script>

<style>
.headerBg{
  background: #eee!important;
}
</style>