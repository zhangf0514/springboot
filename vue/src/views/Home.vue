<template>
<div>
  <el-row :gutter="10" style="margin-bottom: 60px">
    <el-col :span="8">
        <el-card style="color:#409EFF">
          <div><i class="el-icon-coordinate"></i>疫情地区个数</div>
          <div style="padding: 10px 0;text-align: center;font-weight: bold">
            {{ addressNumber }}
          </div>
        </el-card>

    </el-col>
    <el-col :span="8">
      <el-card style="color:#F56C6C">
        <div><i class="el-icon-location"></i>最危险地区</div>
        <div style="padding: 10px 0;text-align: center;font-weight: bold">
          {{ maxAddress }}
        </div>
      </el-card>
    </el-col>
    <el-col :span="8">
      <el-card style="color:#67C23A">
        <div><i class="el-icon-user-solid"></i>疫情总人数</div>
        <div style="padding: 10px 0;text-align: center;font-weight: bold">
          {{ peopleNumber }}
        </div>
      </el-card>
    </el-col>

  </el-row>
  <el-row>
    <el-col :span="12">
      <div id="main" style="width:500px;height:400px"></div>
    </el-col>
    <el-col :span="12">
      <div id="pie" style="width:500px;height:400px"></div>
    </el-col>
  </el-row>


</div>
</template>

<script>
import * as echarts from 'echarts';
export default {
  name: "Home",
  data(){
    return{
      maxAddress:'',
      addressNumber:0,
      peopleNumber:0,
      warnAddress:''
    }
  },
mounted(){
  var chartDom = document.getElementById('main');
  var myChart = echarts.init(chartDom);
  var option;
  var pieDom = document.getElementById('pie');
  var pieChart = echarts.init(pieDom);
  option = {
    title:{
      text: '各季度会员数量统计',
      subtext: '趋势图',
      left:'center'
  },
    xAxis: {
      type: 'category',
      data: []
    },
    yAxis: {
      type: 'value'
    },
    tooltip: {
      trigger: 'item'
    },
    legend: {
      orient: 'vertical',
      left: 'left'
    },
    series: [
      {
        name:'线',
        data: [],
        type: 'line'
      },
      {
        name:'柱',
        data: [],
        type: 'bar'
      },
    ]
  };
  this.request.get("/echarts/yiqing").then(res=>{
    var maxAddress;
    var peopleNumber=0;
    var addressNumber=0;
   for (var key in res.data){
     addressNumber+=1
     peopleNumber+=res.data[key].yiqingnumber
     if(!maxAddress || res.data[key].yiqingnumber>maxAddress.yiqingnumber){
       maxAddress=res.data[key]
     }
   }
   this.peopleNumber=peopleNumber
    this.addressNumber=addressNumber
   this.maxAddress=maxAddress.yiqingaddress
    console.log(this.peopleNumber)
    myChart.setOption(option);
    for (var item in res.data){
      pieOption.series[0].data.push({
        name:res.data[item].yiqingaddress,value:res.data[item].yiqingnumber
      })
    }
    for (var item in res.data){
      option.xAxis.data.push(
       res.data[item].yiqingaddress)
      option.series[0].data.push({
        name:res.data[item].yiqingaddress,value:res.data[item].yiqingnumber
      })
      option.series[1].data.push({
        name:res.data[item].yiqingaddress,value:res.data[item].yiqingnumber
      })
    }
    pieChart.setOption(pieOption)
    myChart.setOption(option)
  })

 var  pieOption = {
    title: {
      text: '各季度会员数量统计',
      subtext: '比例图',
      left: 'center'
    },
    tooltip: {
      trigger: 'item'
    },
    legend: {
      orient: 'vertical',
      left: 'left'
    },
    series: [
      {
        type: 'pie',
        radius: '60%',
        label:{
          normal:{
            show:true,
            position:'inner',
            textStyle:{
              fontWeight:300,
              fontSize:14,
              color:"#fff"
            },
            formatter:'{d}%'
          }
        },
        data: [
        ],
        emphasis: {
          itemStyle: {
            shadowBlur: 10,
            shadowOffsetX: 0,
            shadowColor: 'rgba(0, 0, 0, 0.5)'
          }
        }
      }
    ]
  };


}
}
</script>

<style scoped>

</style>