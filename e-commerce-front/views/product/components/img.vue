<template>
	<div>
		<el-form ref="form" :rules="rules" :model="form" label-width="100px">
			<el-form-item label="商品图片" prop="file">
				<uploadone v-model="form.file" :fileList="fileList"></uploadone>
			</el-form-item>
			<el-form-item label="商品相册" prop="files">
				<uploadmore v-model="form.files" :fileList="fileLists"></uploadmore>
			</el-form-item>
			<!-- <el-form-item>
				<template v-if="add === 1">
					<el-button size="small" @click="prev">上一步</el-button>
					<el-button type="primary" size="small" @click="next">下一步</el-button>
				</template>
				<template v-else>
					<el-button type="primary" size="small" @click="update">保存</el-button>
				</template>
			</el-form-item> -->
		</el-form>
		<div slot="footer" class="dialog-footer">
		  <el-button type="primary" @click="prev">上一步</el-button>
		  <el-button type="primary" @click="next">下一步</el-button>
		  <el-button type="primary" @click="query">查 看</el-button>
		</div>
	</div>
</template>

<script>
	
	export default {
		name: 'ImgList',
		props: {
		  	params: {
		  		type: Object,
		  		default: () => { return {}}
		  	},
		  	id: {
		  		type: Number,
		  		default: null
		  	},
			add: {
				type: Number,
				default: null
			}
	  },
	 
	  created() {
		 // if(this.add != 1){
			// this.getone()
		 // }
	  },
	  data() {
		return {
			form: {
				file: null,
				files: []
			},
			rules: {
				file: [
					{required: true, message: '请上传图片', trigger: 'blur'}
				],
				files: [
					{required: true, message: '请上传相册', trigger: 'blur'}
				]
			},
			fileList: [],
			fileLists: []
		} 
	  },
	  methods: {
			next() {
				this.$refs['form'].validate(valid => {
					if(valid) {
						this.$emit('setForm',this.form)
						this.$emit('next')
					}
				})
				// this.$emit('next')
			},
			query() {
				console.log(this.form)
			},
			prev() {
				this.$emit('prev')
			},
			getone() {
				this.get('/pms-product/getone',{id: this.id},response => {
					this.fileList.push({
						name: response.img,
						url: this.img(response.img)
					})
					const picArr = response.pics.split(',')
					for(let i = 0; i < picArr.length; i++) {
						this.fileLists.push({
							name: picArr[i].trim(),
							url: this.img(picArr[i].trim())
						})
					}
					// console.log(picArr)
					// console.log(response.pics.split(','))
					
				})
			},
			update() {
				this.form.id = this.id
				this.post('/pms-product/updateImg',this.form,(response) => {
					this.$emit('save')
				})
			}
	  }
	}
</script>

<style>
</style>
