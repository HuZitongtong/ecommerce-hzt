<template>
	<div>
		<el-breadcrumb separator="/">
		  <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
		  <el-breadcrumb-item><a href="/role">权限</a></el-breadcrumb-item>
		  <el-breadcrumb-item><a href="/role">角色管理</a></el-breadcrumb-item>
		  <el-breadcrumb-item><a href="/roleusers">分配角色</a></el-breadcrumb-item>
		</el-breadcrumb>
		<div>
			<p style="margin: 20px 0 20px">{{this.form.roleName}}</p>
			<el-transfer
				style="margin-top: 20px;"
				v-model="form.userIds"
				:titles="['所有用户','已选用户']"
				:data="users"
				:props="{
					key: 'id',
					label: 'nickyName'
				}"></el-transfer>
			<div>
				<el-button type="primary" plain size="small" @click="save" style="margin-top: 50px;">保存</el-button>
			</div>			
		</div>
		
	</div>
</template>

<script>
	export default {
		name: 'RoleUsers',
		data() {
			const module = '/ums-role-user'
			return {
				url: {
					getData: module + '/getData' ,
					save: module + '/save',
				},
				form: {
					roleId: '',
					roleName: '',
					userIds: []
				},
				users: []
			}
		},
		created() {
			this.form.roleId = this.$route.query.roleId
			this.form.roleName = this.$route.query.roleName
			this.getData()
		},
		methods: {
			//返回所有有效用户信息
			getData() {
				this.get(this.url.getData, {roleId: this.form.roleId}, response => {
					this.users = response.users
					console.log(response)
					//this.form.userIds.push(response.values[0].userId)
					for(let i = 0; i < response.values.length;i++) {
						this.form.userIds.push(response.values[i].userId)
					}
				})
			},
			save() {
				//console.log(this.form)
				this.post(this.url.save, this.form, response => {
					//回到角色/role页面
					this.$router.push('/role')
				}) 
			}
		}
	}
</script>

<style>
</style>
