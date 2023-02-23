<template>
<el-card style="width: 500px;padding: 20px">
  <el-form  :model="ruleForm" ref="ruleForm"  label-width="80px" size="small" :rules="rules">

      <img v-if="form.avatarurl" :src="form.avatarurl" class="avatar">

    <el-form-item label="原始密码" prop="orginpassword">
      <el-input maxlength="10" minlength="5" v-model="ruleForm.orginpassword"  autocomplete="off"  @blur="rulePassword"></el-input>
    </el-form-item>
    <el-form-item label="修改密码" prop="updatePassword1">
      <el-input v-model="ruleForm.updatePassword1" autocomplete="off"></el-input>
    </el-form-item>
    <el-form-item label="确认密码" prop="updatePassword2">
      <el-input v-model="ruleForm.updatePassword2" autocomplete="off" @blur="twoPassword"></el-input>
    </el-form-item>
    <el-form-item >
    <el-button type="primary" @click="save">确 定</el-button>
      <el-button @click="resetForm('ruleForm')">重置</el-button>
    </el-form-item>
  </el-form>
</el-card>
</template>

<script>
import updatePassword from "@/views/UpdatePassword.vue";

export default {
  name: "Person",
  data(){
    return{
      form:{},
      user:localStorage.getItem("user")?JSON.parse(localStorage.getItem("user")):{},
      ruleForm: {
        orginpassword: '',
        updatePassword1: '',
        updatePassword2: '',
      },
      rules: {
        orginpassword: [
          {required: true, message: '请输入原始密码', trigger: 'blur'},
          {min: 3, max: 5, message: '长度在 3 到 10 个字符', trigger: 'blur'}
        ],
        updatePassword1: [
          {required: true, message: '请输入修改后的密码', trigger: 'change'}
        ],
        updatePassword2: [
          {required: true, message: '确认修改后的密码', trigger: 'change'}
        ],
      }
    }
  },
  created() {
    this.getUser().then(res=>{
      this.form=res
    })
  },
  methods:{
    updatePassword() {
      return updatePassword
    },
   async getUser(){
     return (await this.request.get("/user/username/"+this.user.username)).data
    },
    save(){
      console.log(this.user.password)
      this.user.password=this.ruleForm.updatePassword2
      console.log(this.user.password)
      this.request.post("/user/updatePassword" ,this.user
      ).then(res=>{
            if(res.code==='200'){
              this.$message.success("修改成功,请重新登录")
              this.$emit("refreshUser")
              this.$router.push("/login")
            }else {
              this.$message.error("修改失败")
            }
          }
      )
    },
    handleAvatarSuccess(res){
      this.form.avatarurl=res
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
    rulePassword(){
      if(this.user.password!=this.ruleForm.orginpassword){
        this.$message.error("初始密码不正确,请重新输入")
        this.ruleForm.orginpassword=""
      }
    },
    twoPassword(){
      if(this.ruleForm.updatePassword1!=this.ruleForm.updatePassword2){
        this.$message.error("二次密码不正确,请检查检查")
        this.ruleForm.updatePassword2=""
      }
    }
  }
}
</script>

<style>
.avatar-uploader{
  text-align: center;
  padding-bottom: 10px;
}
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader .el-upload:hover {
  border-color: #409EFF;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 138px;
  height: 138px;
  line-height: 138px;
  text-align: center;
}
.avatar {
  width: 138px;
  height: 138px;
  display: block;
  margin: 0 auto;
  margin-bottom: 20px;
}
</style>