<template>
	<el-container style="height: 800px; border: 1px solid #eee">
	  <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
	   <Menu></Menu>
	  </el-aside>
	  
	  <el-container>
	    <el-header>
			<div class="title">
				<span><h2>Neu品牌服装销售管理系统</h2></span>			
			</div>
			<div style="text-align: right; font-size: 12px">
				<el-dropdown@command="handleCommand" >
				  <i class="el-icon-setting" style="margin-right: 15px"></i>
				  <el-dropdown-menu slot="dropdown">
				    <el-dropdown-item commend="退出">退出登陆</el-dropdown-item>
				  </el-dropdown-menu>
				</el-dropdown>
				<span>{{username}}</span>
			</div>
	    </el-header>	    
	    <el-main>
	      <router-view></router-view>
	    </el-main>
	  </el-container>
	</el-container>
</template>

<script>
	import Menu from '@/components/menu'
	 export default {
		 name: 'Index',
		 components: {
			 Menu
		 },
		 created() {
		 	this.username = this.$route.query.username
		 },
	    data() {
	      return {
			  username:'',
	        tableData: Array(20).fill(item)
	      }
	    },
		methods: {
			handleCommand(commend) {
				this.$router.push('/login')
			}
		}
	  };
</script>

<style>
	.el-header {
	   background-color: #545c64;
	    color: #fff;
	    line-height: 60px;
		border:-webkit-link;
	  }
	  .el-icon-setting {
		  color: #fff;
	  }
	  .title {
		  line-height: 30px;
		  float: left;
		  color: #409EFF;
	  }
	 /* .el-aside {
	    color: #333;
	  } */
</style>
