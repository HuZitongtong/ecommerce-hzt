<template>
  <div id="app">
    <router-view></router-view>
  </div>
</template>

<script>


export default {
  name: 'app',
  
}
</script>

<style lang="less">
	/* html{
		font-family: 'Avenir', Helvetica, Arial, sans-serif;
		font-size: 16px;
		color: #2c3e50;
	} */
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  //text-align: center;
  color: #2c3e50;
  //margin-top: 60px;
}
.my_filter {
	margin-bottom: 10px;
	
	.el-input{
		width: auto;
		margin-right: 10px;
	}
}
.dialog-footer {
	text-align: right;
}
.pagination {
	text-align: center;
}
.my_menu {
	text-align: center;
}

</style>
