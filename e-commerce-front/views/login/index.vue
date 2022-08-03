<template>
	<div>
		<el-card class="login-form-layout">
			<el-form autoComplete="on"
			         :model="loginForm"
			         ref="loginForm"
			         label-position="left">
				<h2 class="login-title color-main">Neu品牌服装销售管理系统</h2>
				<el-form-item prop="username">
				  <el-input name="username"
				            type="text"
				            v-model="loginForm.username"
				            autoComplete="on"
				            placeholder="请输入用户名">
					<span slot="prefix">
					  <i class="el-icon-user-solid"></i>
					</span>
				  </el-input>
				</el-form-item>
				<el-form-item prop="password">
				  <el-input name="password"
				            v-model="loginForm.password"
				            autoComplete="on"
							show-password
				            placeholder="请输入密码">
				  <span slot="prefix">
				    <i class="el-icon-lock"></i>
				  </span>
				  </el-input>
				</el-form-item>
				<el-form-item style="margin-bottom: 60px;text-align: center">
				  <el-button style="width: 45%" type="primary" @click="login">
				    登录
				  </el-button>
				  <el-button style="width: 45%" type="primary">
				    找回密码
				  </el-button>
				</el-form-item>
			</el-form>
		</el-card>
	</div>
</template>

<script>
	export default {
		name: 'Login',
		data() {
			return {
				url: {
					login: '/ums_user/login'
				},
				loginForm: {
					username:'',
					password:''
				},
				rules:[]
			}
		},
		methods: {
			login() {
				this.post(this.url.login,this.loginForm,response => {
					// console.log(response)
					this.$store.commit('SET_TOKEN',response)
					this.$router.push({
						path:'/',
						query:{
							username:this.loginForm.username
						}
					})
				})
			}
		}
	}
</script>

<style lang="less" scoped>
	.login-form-layout {
	  position: absolute;
	  left: 0;
	  right: 0;
	  width: 360px;
	  margin: 140px auto;
	  border-top: 10px solid #409EFF;
	}
	
	.login-title {
	  text-align: center;
	}
	
	.login-center-layout {
	  background: #409EFF;
	  width: auto;
	  height: auto;
	  max-width: 100%;
	  max-height: 100%;
	  margin-top: 200px;
	}
	.color-main {
		color: #409EFF;
	}
</style>
