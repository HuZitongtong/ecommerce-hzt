<template>
	<div class="my_menu">
		<el-menu
		 :router="true"
		 :default-openeds="['2','3','5']"
		 background-color="#545c64"
		 text-color="#fff"
	     active-text-color="#ffd04b">		
		  <el-menu-item index="home">
			  <i class="el-icon-s-home"></i>
			  首页</el-menu-item>
		  <el-submenu index="/pms">
		    <template slot="title"><i class="el-icon-s-goods"></i>商品</template>
		    <el-menu-item-group>
		      <el-menu-item index="/category">分类管理</el-menu-item>
			  <el-menu-item index="/product">商品管理</el-menu-item>
			  <el-menu-item index="/productAdd">添加商品</el-menu-item>
		    </el-menu-item-group>
		</el-submenu>
		<el-submenu index="4">
		    <template slot="title"><i class="el-icon-s-order"></i>订单</template>
		    <el-menu-item-group>
		      <el-menu-item index="/order">订单列表</el-menu-item>
		      <el-menu-item index="/setting">订单设置</el-menu-item>
			  <el-menu-item index="/returnOrder">退货申请处理</el-menu-item>
			  <el-menu-item index="/returnReason">退货原因设置</el-menu-item>
		    </el-menu-item-group>
		</el-submenu>
		<el-submenu index="5">
		    <template slot="title"><i class="el-icon-collection"></i>营销</template>
		    <el-menu-item-group>
			  <!-- <el-menu-item index="/dressUp">搭配推荐</el-menu-item> -->
			  <el-menu-item index="/recommend">专题推荐</el-menu-item>
			  <el-menu-item index="/advertise">广告列表</el-menu-item>
		    </el-menu-item-group>
		</el-submenu>
		<el-submenu index="2">
		  <template slot="title"><i class="el-icon-menu"></i>权限</template>
		  <el-menu-item-group>
		    <el-menu-item index="/user">用户管理</el-menu-item>
		    <el-menu-item index="/role">角色管理</el-menu-item>
					  <el-menu-item index="2-3">权限管理</el-menu-item>
		  </el-menu-item-group>
		</el-submenu>
		</el-menu>
	</div>
	
</template>

<script>
	export default {
		name: 'memu',
	}
</script>

<style>
</style>
