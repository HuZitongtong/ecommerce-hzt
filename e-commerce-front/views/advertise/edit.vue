<template>
	<div>
		<el-breadcrumb separator="/">
		  <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
		  <el-breadcrumb-item><a href="/advertise">营销</a></el-breadcrumb-item>
		  <el-breadcrumb-item>添加广告</a></el-breadcrumb-item>
		</el-breadcrumb>
		 <el-card class="form-container" shadow="never">
			<el-form :model="homeAdvertise"
			          :rules="rules"
			          ref="form"
			          label-width="150px"
			          size="small">
				<el-form-item label="广告名称：" prop="name">
				  <el-input v-model="homeAdvertise.name" class="input-width"></el-input>
				</el-form-item>
				<el-form-item label="广告位置：">
					 
					 <el-select v-model="homeAdvertise.type" size="small">
						 <el-option v-for="item in typeOptions" :key="item.value" :label="item.name" :value="item.value">
						 </el-option>
					 </el-select>
					
				</el-form-item>
				<el-form-item label="上线/下线：">
				  <el-radio-group v-model="homeAdvertise.status">
				    <el-radio :label="0">下线</el-radio>
				    <el-radio :label="1">上线</el-radio>
				  </el-radio-group>
				</el-form-item>
				<el-form-item label="广告图片：" prop="file">
					<uploadone v-model="homeAdvertise.file" :fileList="filelist"></uploadone>
				</el-form-item>
				<el-form-item label="排序：">
				  <el-input v-model="homeAdvertise.sort" class="input-width"></el-input>
				</el-form-item>
				<el-form-item label="广告链接：" prop="url">
				  <el-input v-model="homeAdvertise.url" class="input-width"></el-input>
				</el-form-item>
				<el-form-item label="广告备注：">
				  <el-input
				    class="input-width"
				    type="textarea"
				    :rows="5"
				    placeholder="请输入内容"
				    v-model="homeAdvertise.note">
				  </el-input>
				</el-form-item>
				<el-form-item>
				  <el-button type="primary" @click="save">提交</el-button>
				  <!-- <el-button v-if="!isEdit" @click="resetForm('homeAdvertiseFrom')">重置</el-button> -->
				</el-form-item>
			</el-form>
		 </el-card>
	</div>
</template>

<script>
	export default {
		name: 'AdvertiseEdit',
		data() {
			const module = '/sms-advertise'
			return {
				id: '',
				url: {
					getone: module + '/getone',
					add: module + '/add',
					update: module + '/update'
				},
				typeOptions: [
					{name: 'pc首页轮播', value: 0},
					{name:'app首页轮播', value: 1}
				],
				homeAdvertise: {
					name: '',
					type: 0,
					status: '',
					file: null,
					sort: '',
					url:'',
					note:'',
				},
				filelist: [],
			}
		},
		created() {
			this.id = this.$route.query.id
			if (this.id) {
				//为修改操作
				this.getone()
			} else {
				//为添加操作
			}
		},
		methods: {
			save() {
				this.post(this.id ? this.url.update : this.url.add, this.homeAdvertise, () => {
					this.$router.push('/advertise')
				})
			},
			getone() {
				this.get(this.url.getone,{id: this.id}, response => {
					console.log(response)
					for (let key in this.homeAdvertise) {
						this.homeAdvertise[key] = response[key]
					} 
					// this.homeAdvertise.id = response.id
					this.filelist.push({
						name: response.img,
						url: this.img(response.img)
					})
				})
			},
		
		}
	}
</script>

<style scoped lang="less">
	.form-container {
		margin-top: 25px;
	}
	.input-width {
		width: auto;
	}
</style>
