<template>
<div>
  <div style="margin: 10px 0">
    <el-input style="width: 200px" suffix-icon="el-icon-search" placeholder="请输入名称" v-model="name"></el-input>
<!--    <el-input style="width: 200px" suffix-icon="el-icon-message" placeholder="请输入类型" v-model="email" class="ml-5"></el-input>
    <el-input style="width: 200px" suffix-icon="el-icon-position" placeholder="请输入商家名" v-model="address" class="ml-5"></el-input>-->
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
    <el-upload action="http://localhost:9090/product/import" style="display: inline-block" :show-file-list="false" accept=".xlsx" :on-success="handleExcelImportSucess">
        <el-button class="ml-5" type="primary">导入<i class="el-icon-bottom"></i></el-button>
    </el-upload>
    <el-button class="ml-5" type="primary" @click="exp">导出<i class="el-icon-top"></i></el-button>
  </div>
<!--  <el-table :data="tableData" border stripe :header-cell-class-name="headerBg"   @selection-change="handleSelectionChange">-->
<!--    <el-table-column type="selection" width="55"></el-table-column>-->
<!--    <el-table-column prop="id" label="ID" width="80">-->
<!--    </el-table-column>-->
<!--    <el-table-column prop="name" label="用户名" width="140">-->
<!--    </el-table-column>-->
<!--    <el-table-column prop="price" label="价格" width="120">-->
<!--    </el-table-column>-->
<!--    <el-table-column prop="praise" label="点赞量">-->
<!--    </el-table-column>-->
<!--    <el-table-column prop="number" label="销售数量">-->
<!--    </el-table-column>-->
<!--    <el-table-column prop="miaoshu"  label="描述"  style="overflow: hidden">-->
<!--    </el-table-column>-->
<!--    <el-table-column prop="itemid" label="商品类型">-->
<!--    </el-table-column>-->
<!--    <el-table-column prop="userid" label="商店名称">-->
<!--    </el-table-column>-->
<!--    <el-table-column fixed="right" label="操作" width="200" align="center">-->
<!--      <template slot-scope="scope">-->
<!--        <el-button type="success" @click="handleEdit(scope.row)">编辑<i class="el-icon-edit"></i></el-button>-->
<!--        <el-popconfirm-->
<!--            class="ml-5"-->
<!--            confirm-button-text='确定'-->
<!--            cancel-button-text='我再想想'-->
<!--            icon="el-icon-info"-->
<!--            icon-color="red"-->
<!--            title="您确定删除吗？"-->
<!--            @confirm="handleDel(scope.row.id)"-->
<!--        >-->
<!--          <el-button type="danger" slot="reference">删除<i class="el-icon-remove-outline"></i></el-button>-->
<!--        </el-popconfirm>-->

<!--      </template>-->
<!--    </el-table-column>-->
<!--  </el-table>-->

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
              <el-form-item label="商家电话">
                <span>{{ props.row.shopphone }}</span>
              </el-form-item>
              <el-form-item label="点赞量">
                <span>{{ props.row.praise }}</span>
              </el-form-item>
              <el-form-item label="销售量">
                <span>{{ props.row.number }}</span>
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
        label="所属店名"
        prop="shopname">
    </el-table-column>
    <el-table-column
        label="商品价格(元)"
        prop="price">
    </el-table-column>
    <el-table-column
        label="销量"
        prop="number">
    </el-table-column>
    <el-table-column
        label="地址"
        prop="shopaddress">
    </el-table-column>
    <el-table-column  label="操作" width="200" align="center">
            <template slot-scope="scope">
              <el-button type="success" @click="handleEdit(scope.row)">编辑<i class="el-icon-edit"></i></el-button>
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
  name: "User",
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
      this.request.get("/product/page",{
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
      this.name=""
      this.itemid=""
      this.userid=""
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

</style>