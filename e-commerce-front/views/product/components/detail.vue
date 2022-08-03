<template>
	<div>
		<!-- <VueEditor useCustomImageHandler @image-added="handleImageAdded" v-model="content"></VueEditor> -->
		<myEditor v-model="content"></myEditor>
		<div slot="footer" class="dialog-footer">
		  <el-button type="primary" @click="prev">上一步</el-button>
		  <el-button type="primary" @click="save">保 存</el-button>
		  <el-button type="primary" @click="query">查 看</el-button>
		</div>
	</div>
</template>

<script>
	export default {
		name: 'Detail',
		data() {
			return {
				content: ''
			}
		},
		created() {
			
		},
		methods: {
			prev() {
				this.$emit('prev')
			},
			query() {
				
			},
			save() {
				this.$emit('setForm', {detailHtml: this.content})
				this.$emit('save')
				
				this.$router.push('/product')
			},
			
			
		}
	}
</script>

<style scoped lang="less">
	.dialog-footer{
		margin-top: 15px;
	}
</style>
