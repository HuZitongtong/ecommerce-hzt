<template>
	<div>
		<el-upload
			:file-list="files"
			action="http://www.abc.com"
			list-type="picture-card"
			:auto-upload="false"
			:limit="1"
			:on-change="changefile"
			:on-remove="removefile">
			<i class="el-icon-plus"></i>
		</el-upload>
	</div>
</template>

<script>
export default {
  name: 'UploadOne',
	props: {
		value: {
			type: File,
			default: () => { return null}
		},
		fileList: {
			type: Array,
			default: () => {return []}
		}
	},
	created() {
		this.files = this.fileList
	},
  data() {
    return {
			files: []
    }
  },
  methods: {
		changefile(file) {
			this.$emit('input',file.raw)
		},
		removefile() {
			this.$emit('input',null)
		}
  }
}
</script>
<style scoped lang="less">
</style>
