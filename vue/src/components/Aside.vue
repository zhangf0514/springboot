<template>
  <el-menu :default-openeds="['1', '3']" style="min-height: 100%;overflow-x:hidden "
           background-color="rgb(48,65,86)"
           active-text-color="#ffd04b"
           text-color="#fff"
           :collapse-transition="false"
           :collapse="isCollapse"
           router
  >
    <div style="height: 60px;line-height: 60px;text-align: center">
      <img src="../assets/logo.png" alt="" style="width: 20px; position: relative;top: 5px;margin-right: 5px">
      <b style="color: white" v-show="logoTextShow"> 后台管理系统</b>
    </div>
    <div @click="tiao">
        <el-menu-item >
          <i class="el-icon-house"></i>
          <span slot="title">主页</span>
      </el-menu-item>
    </div>
    <el-submenu index="2" >
      <template slot="title">
        <i class="el-icon-menu"></i>
        <span slot="title">系统管理</span>
      </template>
      <el-menu-item index="/Yiqing" v-if="user.role!=='用户'">
        <template slot="title">
          <i class="el-icon-s-marketing"></i>
          <span slot="title">疫情管理</span>
        </template>
      </el-menu-item>
      <el-menu-item index="/adminShop" v-if="user.role!=='用户'">
        <template slot="title">
          <i class="el-icon-eleme"></i>
          <span slot="title">管理员店铺管理</span>
        </template>
      </el-menu-item>
        <el-menu-item index="/adminuser" v-if="user.role!=='用户'">
          <template slot="title">
            <i class="el-icon-s-shop"></i>
            <span slot="title">管理员商家管理</span>
          </template>
        </el-menu-item>
      <el-menu-item index="/admincustomer" v-if="user.role!=='用户'">
        <template slot="title">
          <i class="el-icon-s-custom"></i>
          <span slot="title">管理员用户管理</span>
        </template>
      </el-menu-item>
      <el-menu-item index="/adminFood" v-if="user.role!=='用户' ">
        <template slot="title">
          <i class="el-icon-food"></i>
          <span slot="title">管理员食物管理</span>
        </template>
      </el-menu-item>
      <el-menu-item index="/userShop" v-if="user.role=='用户'">
        <template slot="title">
          <i class="el-icon-food"></i>
          <span slot="title">店家店铺管理</span>
        </template>
      </el-menu-item>
      <el-menu-item index="/product" v-if="user.role=='用户' && enable">
        <template slot="title">
          <i class="el-icon-food"></i>
          <span slot="title">店家商品管理</span>
        </template>
      </el-menu-item>
<!--      <el-menu-item index="/person" v-if="user.role==='用户'">-->
<!--        <template slot="title">-->
<!--          <i class="el-icon-s-shop"></i>-->
<!--          <span slot="title">店家信息管理</span>-->
<!--        </template>-->
<!--      </el-menu-item>-->
      <el-menu-item index="/order" v-if="user.role==='用户' && enable">
        <template slot="title">
          <i class="el-icon-document-copy"></i>
          <span slot="title">店家订单管理</span>
        </template>
      </el-menu-item>
<!--      <el-menu-item index="/menu" v-if="user.role==='用户'">-->
<!--        <template slot="title">-->
<!--          <i class="el-icon-s-custom"></i>-->
<!--          <span slot="title">菜单管理</span>-->
<!--        </template>-->
<!--      </el-menu-item>-->
<!--      <el-menu-item index="/file" v-if="user.role==='用户'">-->
<!--        <template slot="title">-->
<!--          <i class="el-icon-document"></i>-->
<!--          <span slot="title">文件管理</span>-->
<!--        </template>-->
<!--      </el-menu-item>-->
      <el-menu-item index="/food" v-if="user.role==='用户'&& enable">
        <template slot="title">
          <i class="el-icon-document"></i>
          <span slot="title">店家菜单管理</span>
        </template>
      </el-menu-item>
      <el-menu-item index="/im" v-if="user.role==='用户'&& enable">
        <template slot="title">
          <i class="el-icon-chat-dot-square"></i>
          <span slot="title">聊天</span>
        </template>
      </el-menu-item>

    </el-submenu  >
  </el-menu>
</template>

<script>
export default {
  data(){
    return{
      user:JSON.parse(localStorage.getItem("user")),
      enable:JSON.parse(localStorage.getItem("state"))
    }
  },
  name: "Aside",
  props:{
    isCollapse:Boolean,
    logoTextShow:Boolean
  },
  methods:{
    handleSelect(){
      this.$route.fullPath.split("/")
    },
    tiao(){
      this.$router.push("/")
    }
  }
}
</script>

<style scoped>

</style>