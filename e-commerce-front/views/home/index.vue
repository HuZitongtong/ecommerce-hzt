<template>
	<div>
		<el-row :gutter="20">
		  <el-col :span="6">
			  <div class="total-frame">
				  <el-card shadow="never">
					<img src="../../assets/images/home_order.png" class="total-icon">
					<div class="total-title">今日订单总数</div>
					<div class="total-value">200</div>
				  </el-card>	
			  </div>	
		  </el-col>
		  <el-col :span="6">
			  <div class="total-frame">
					<el-card shadow="never">	
						<img src="../../assets/images/home_today_amount.png" class="total-icon">
						<div class="total-title">今日销售总额</div>
						<div class="total-value">￥5000.00</div>
					</el-card>
			  </div>
		  </el-col>
		  <el-col :span="6">
			  <div class="total-frame">
				  <el-card shadow="never">
					  <img src="../../assets/images/home_yesterday_amount.png" class="total-icon"/>
					  <div class="total-title">昨日销售总额</div>
					  <div class="total-value">￥50000.00</div>
				  </el-card>  
			  </div>
		  </el-col>
		</el-row>
		<el-row :gutter="20">
		  <el-col :span="20"><div class="grid-content bg-purple"></div></el-col>
		 <!-- <el-col :span="8"><div class="grid-content bg-purple"></div></el-col>
		  <el-col :span="4"><div class="grid-content bg-purple"></div></el-col>
		  <el-col :span="4"><div class="grid-content bg-purple"></div></el-col> -->
		</el-row>
		<el-row :gutter="20">
		  <el-col :span="4"><div class="grid-content bg-purple"></div></el-col>
		  <el-col :span="16"><div class="grid-content bg-purple"></div></el-col>
		  <el-col :span="4"><div class="grid-content bg-purple"></div></el-col>
		</el-row>

		<!-- <div class="total-layout">
		  <el-row :gutter="20">
		    <el-col :span="6">
				<div class="total-frame">
				<el-card shadow="never">
				  <img src="../../assets/images/home_order.png" class="total-icon">
				  <div class="total-title">今日订单总数</div>
				  <div class="total-value">200</div>
				</el-card>	
				</div>				      
		    </el-col>
		    <el-col :span="6">
		      <div class="total-frame">
				<el-card shadow="never">	
				    <img src="../../assets/images/home_today_amount.png" class="total-icon">
					<div class="total-title">今日销售总额</div>
					<div class="total-value">￥5000.00</div>
				</el-card>
		      </div>
		    </el-col>
		    <el-col :span="6">
		      <div class="total-frame">
				<el-card shadow="never">
					
					<div class="total-title">昨日销售总额</div>
					<div class="total-value">￥5000.00</div>
				</el-card>     
		      </div>
		    </el-col>
		    <el-col :span="6">
		      <div class="total-frame">
		       <svg-icon icon-class="total-week" class="total-icon">
		        </svg-icon>
		        <div class="total-title">近7天销售总额</div>
		        <div class="total-value">￥50000.00</div>
		      </div>
		    </el-col>
		  </el-row>
		</div> -->
	</div>
</template>

<script>
	export default {
		name: 'Home'
	}
</script>

<style lang="less">
.total-title {
	color: #808080;
}
//最小高度是和bottom的块的最小距离
.total-frame {
	border-radius: 4px;
	min-height: 150px;
	height: 5px;
	width: 250px;
}
.total-icon {
	max-height: 50px;
	max-width: 50px;
}
// ::v-deep img{
// 	width: 10%;
// }

 .el-row {
    margin-bottom: 20px;
    &:last-child {
      margin-bottom: 0;
    }
  }
  .el-col {
    border-radius: 4px;
  }
  .bg-purple-dark {
    background: #99a9bf;
  }
  .bg-purple {
    background: #d3dce6;
  }
  .bg-purple-light {
    background: #e5e9f2;
  }
  .grid-content {
    border-radius: 4px;
    min-height: 70px;
  }
  .row-bg {
    padding: 10px 0;
    background-color: #f9fafc;
  }
</style>
