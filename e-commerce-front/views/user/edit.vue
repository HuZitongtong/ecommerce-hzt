<template>
	<div>
		 <el-form ref="myform" :model="form" label-width="120px" :rules="rules">
		    <el-form-item label="登录名" prop="loginName">
		      <el-input v-model="form.loginName" autocomplete="off"></el-input>
		    </el-form-item>
			<el-form-item label="昵称" prop="nickyName">
				<el-input v-model="form.nickyName"></el-input>
			</el-form-item>
		    <el-form-item v-if="!this.id" label="密码" prop="rawPassword">
				<el-input v-model="form.rawPassword" show-password></el-input>
			</el-form-item>
			<el-form-item label="手机号码" prop="phone">
				<el-input v-model="form.phone"></el-input>
			</el-form-item>
			<el-form-item label="邮箱" prop="email">
				<el-input v-model="form.email"></el-input>
			</el-form-item>
			<el-form-item label="头像" prop="file">
				<uploadone v-model="form.file" :fileList="filelist"></uploadone>
				<!-- <el-upload
					:file-list="filelist"
					action="http://www.abc.com"
					list-type="picture-card"
					:auto-upload="false"
					:limit="1"
					:on-change="handleChangeFile"
					:on-remove="handleRemoveFile">
					<i class="el-icon-plus"></i>
				</el-upload> -->
			</el-form-item>
		  </el-form>
		  <div slot="footer" class="dialog-footer">
		    <el-button @click="close">取 消</el-button>
		    <el-button type="primary" @click="save">确 定</el-button>
		  </div>
	</div>
</template>

<script>
	
	export default {
		name: 'UserEdit',
		props: {
			id: {
				type: Number,
				default: null
			}
		},
		
		created() {
			//获取父组件传过来的数据 此方法为动态接收 this.$ attrs
			//可使用props来静态接收
			//动态和静态只能使用一种
			if (this.id) {
				//为修改操作
				this.getone()
			} else {
				//为添加操作
				this.rules.file.push({required: true, message: '请上传头像', trigger: 'blur'})
			}
		},
		data() {
			const module = '/ums_user'
			return {
				url: {
					add: module + '/add',
					getone: module + '/getone',
					update: module + '/update'			
				},
				form: {
					loginName:'',
					nickyName:'',
					rawPassword:'',
					phone:'',
					email:'',
					file:null
				},
				filelist: [],
				rules: {
					//表单验证规则
					loginName: [
						{ required: true, message: '请输入登录名', trigger: 'blur' },
						{ min: 1, max: 20, message: '不能超过20个字符', trigger: 'blur' }
					],
					nickyName: [
						{ required: true, message: '请输入昵称', trigger: 'blur' },
						{ min: 1, max: 20, message: '不能超过20个字符', trigger: 'blur' }
					],
					rawPassword: [
						{ required: true, message: '请输入密码', trigger: 'blur' }
					],
					phone: [
						{ required: true, message: '请输入手机号', trigger: 'blur' },
						{ pattern: /^1([38][0-9]|4[5-9]|5[0-3,5-9]|66|7[0-8]|9[89])[0-9]{8}$/,message: '请输入正确的手机号', trigger: 'blur'}
					],
					email: [
						{ required: true, message: '请输入邮箱', trigger: 'blur' },
						{ min: 1, max: 50, message: '不能超过50个字符', trigger: 'blur' },
						{ type: 'email', message: '请输入正确的邮箱', trigger: 'blur'}
					],
					file: []
				}
			}
		},
		methods: {
			//使用组件后不需要使用该功能实现双向绑定
			//图片上传
			// handleChangeFile(file) {
			// 	this.form.file = file.raw
			// },
			// handleRemoveFile() {
			// 	this.form.file = null 
			// },
			//通过id查到该用户的数据 填写表单
			getone() {
				this.get(this.url.getone,{id: this.id},response => {
					for (let key in this.form) {
						this.form[key] = response[key]
					} 
					this.form.id = response.id
					this.filelist.push({
						name: response.icon,
						url: this.img(response.icon)
					})
				})
			},
			//保存修改/保存新建用户
			save() {
				this.$refs['myform'].validate((flag) => {
					if(flag) {
						this.post(this.id ? this.url.update : this.url.add,this.form,() => {
							//console.log(response)
							this.$emit('update:show', false)
							this.$emit('getTableData')
						}) 
					}
				})
				
			},
			//关闭页面
			close() {
				this.$emit('update:show', false)
			}
		}
	}
</script>

<style>
</style>
