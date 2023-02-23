<template>
<div>
  <div style="margin: 10px 0">
    <el-input style="width: 200px" suffix-icon="el-icon-search" placeholder="请输入用户名称" v-model="customername"></el-input>
   <el-input style="width: 200px" suffix-icon="el-icon-phone" placeholder="请输入买家电话" v-model="phone" class="ml-5"></el-input>
    <el-button class="ml-5" type="primary" @click="load">搜索</el-button>
    <el-button type="warning" @click="reset">重置</el-button>
  </div>
  <div style="margin: 10px 0">
    <el-upload action="http://localhost:9090/product/import" style="display: inline-block" :show-file-list="false" accept=".xlsx" :on-success="handleExcelImportSucess">
      <el-button class="ml-5" type="primary">导入<i class="el-icon-bottom"></i></el-button>
    </el-upload>
    <el-button class="ml-5" type="primary" @click="exp">导出<i class="el-icon-top"></i></el-button>
  </div>


  <el-table
      :data="tableData"
      style="width: 100%"
      border stripe :header-cell-class-name="headerBg"   @selection-change="handleSelectionChange">
    <el-table-column type="selection" width="55"></el-table-column>
    <el-table-column type="expand" label="展开">
      <template slot-scope="props">
        <img :src="props.row.imageurl" alt="" style="width: 90px;float: left;padding-top:0.8%;">
        <div  class="demo-table-expand-right" >
        <el-form label-position="left" inline class="demo-table-expand" >
              <el-form-item label="所属店铺" >
                <span>{{ props.row.shopname }}</span>
              </el-form-item>
              <el-form-item label="店铺地址">
                <span>{{ props.row.shopaddress }}</span>
              </el-form-item>
              <el-form-item label="商品单价/元">
                <span>{{ props.row.price }}</span>
              </el-form-item>
              <el-form-item label="商品描述">
                <span>{{ props.row.miaoshu }}</span>
              </el-form-item>
        </el-form>
        </div>
      </template>
    </el-table-column>
    <el-table-column
        label="商品名称"
        prop="name">
    </el-table-column>
    <el-table-column
        label="商品价格(元)"
        prop="price">
    </el-table-column>
    <el-table-column
        label="买家姓名"
        prop="customername">
    </el-table-column>
    <el-table-column
        label="买家地址"
        prop="address">
    </el-table-column>
    <el-table-column
        label="买家电话"
        prop="phone">
    </el-table-column>
    <el-table-column
        label="订单状态"
       prop="orderstate">
      <template slot-scope="scope">
        <span class="nofinshed" v-if="scope.row.orderstate==0">未完成</span>
        <span class="finshed" v-if="scope.row.orderstate==1">已完成</span>
      </template>
    </el-table-column>
    <el-table-column  label="状态"  align="center">
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

  <el-dialog title="商品信息" :visible.sync="dialogFormVisible" width="30%" >
    <el-form label-width="80px" size="small">
      <el-form-item label="商品名称">
        <el-input v-model="form.name" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="价格">
        <el-input v-model="form.price" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="描述">
        <el-input v-model="form.miaoshu" type="textarea"  autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="商品类型">
        <el-input v-model="form.itemname" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="所属商店">
        <el-input v-model="form.shopname" autocomplete="off"></el-input>
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
  name: "Order",
  data(){
    return{
      tableData:[],
      total:0,
      pageNum:1,
      pageSize:10,
      name:'',
      price:'',
      miaoshu:'',
      userid:'',
      itemid:'',
      customername: '',
      phone: '',
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
      this.request.get("/order/page",{
        params:{
          pageNum:this.pageNum,
          pageSize:this.pageSize,
          customername:this.customername,
          phone:this.phone,
          ordersid:localStorage.getItem("shopId")
        }
      })
          .then(res => {
            this.tableData=res.data.records
            this.total=res.data.total
          })
    },
    reset(){
      this.customername=""
      this.shopname=""
      this.load()
    },
    save(){
      this.request.post("/product/",this.form).then(res=>{
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
      this.request.delete("/product/"+id).then(res=>{
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
      this.request.post("/product/del/batch",ids).then(res=>{
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
      window.open("http://localhost:9090/product/export")
    },
    handleExcelImportSucess(){
      this.$message.success("文件导入成功");
      this.load()
    }
  }
}
</script>

<style>
.headerBg{
  background: #eee!important;
}

.demo-table-expand-right{
  margin-left: 110px;
}
.demo-table-expand{
  font-size: 0;
}
.demo-table-expand label {
  width: 90px;
  color: #99a9bf;
}
.demo-table-expand .el-form-item {
  margin-right: 0px;
  margin-bottom: 0;
  width: 50%;
}
.el-form-item__content:last-child{
  width: 70%;
}
.finshed{
  color: #67C23A;
}
.nofinshed{
  color: red;
}
</style>