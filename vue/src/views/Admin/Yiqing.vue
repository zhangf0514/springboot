<template>
  <div>
    <div style="margin: 10px 0">
      <el-input style="width: 200px" suffix-icon="el-icon-position" placeholder="请输入地址" v-model="yiqingaddress" class="ml-5"></el-input>
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
      <el-table-column prop="yiqingaddress" label="地区" >
      </el-table-column>
      <el-table-column prop="yiqingnumber" label="感染人数">
      </el-table-column>

      <el-table-column  label="增加/减少" >
        <template slot-scope="scope">
        <i class="el-icon-circle-plus iconLeft"   @click="addNumber(scope.row)" ></i>
          <i class="el-icon-remove iconRight" slot="reference" @click="minusNumber(scope.row)" ></i>
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
              @confirm="handleDel(scope.row)"
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

    <el-dialog title="疫情信息" :visible.sync="dialogFormVisible" width="30%" >
      <el-form label-width="80px" size="small">
        <el-form-item label="地址">
          <el-input v-model="form.yiqingaddress" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="感染人数">
          <el-input v-model="form.yiqingnumber" autocomplete="off"></el-input>
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
      yiqingnumber:0,
      yiqingaddress:'',
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
      this.request.get("/yiqing/page",{
        params:{
          pageNum:this.pageNum,
          pageSize:this.pageSize,
          yiqingaddress:this.yiqingaddress,
        }
      })
          .then(res => {
            console.log(res)
            this.tableData=res.data.records
            this.total=res.data.total
          })
    },
    reset(){
      this.yiqingaddress=""
      this.load()
    },
    save(){
      this.request.post("/yiqing/",this.form).then(res=>{
            if(res.code==="200"){
              this.$message.success("保存成功")
              this.dialogFormVisible=false
              this.request.get(`/shop/updateByYiState`,
                  {
                    params:{
                      address:this.form.yiqingaddress,
                      state:false
                    }
                  }
              )
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
    handleDel(item){
      this.request.delete("/yiqing/"+item.id).then(res=>{
        if(res.code==="200"){
          this.$message.success("删除成功")
          this.request.get(`/shop/updateByYiState`,
              {
                params:{
                  address:item.yiqingaddress,
                  state:true
                }
              }
          )
          this.load()
        }else {
          this.$message.error("删除失败")
        }
      })
    },
    delBatch(){
      let ids=this.multipleSelection.map(v => v.id)
      this.request.post("/yiqing/del/batch",ids).then(res=>{
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
      this.request.get("/yiqing/updateByIdState",{
        params:{
          id:row.id,
          state:row.state
        }}).then(res=> {
        if (res.code==='200'){
          this.$message.success("操作成功")
        }
      })
      this.tableData.enable=row.enable
    },
    changeEnable(row){
      this.request.get("/yiqing/updateByIdEnable",{
        params:{
          id:row.id,
          enable:row.enable
        }}).then(res=> {
        if (res.code==='200'){
          this.$message.success("操作成功")
        }
      })
      this.tableData.enable=row.enable
    },
    minusNumber(item){
      console.log(item.yiqingnumber)
      if(item.yiqingnumber-1!==0){
      this.request.post("/yiqing/", JSON.stringify({ id:item.id,
        yiqingaddress:item.yiqingaddress,
        yiqingnumber:-1}
      )).then(res=>{
            if(res.code==="200"){
              this.load()
            }})
      } else{
       this.handleDel(item.id)
      }},
    addNumber(item){
      this.request.post("/yiqing/",
          JSON.stringify({ id:item.id,
        yiqingaddress:item.yiqingaddress,
        yiqingnumber:1}
      )).then(res=>{
        if(res.code==="200"){
          this.load()
        }})
    },
  }
}
</script>

<style>
.headerBg{
  background: #eee!important;
}
.iconLeft{
  font-size: 30px;
  margin: 20px;
  color: #67C23A;

}
.iconRight{
  font-size: 30px;
  margin: 20px;
  color: red;
}

</style>