<template>
	<div>
		<el-breadcrumb separator="/" style="margin-bottom: 15px;">
			<el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
			<el-breadcrumb-item><a href="/order">订单</a></el-breadcrumb-item>
			<el-breadcrumb-item><a href="/setting">订单设置</a></el-breadcrumb-item>
		</el-breadcrumb>
		<el-card class="form-container" shadow="never" style="width: 650px;margin-left: 150px;">
			<el-form :model="orderSetting"
					 ref="orderSettingForm"
					 :rules="rules"
					 label-width="150px">
				<el-form-item label="秒杀订单超过：" prop="flashOrderOvertime">
				  <el-input v-model="orderSetting.flashOrderOvertime" class="input-width">
				    <template slot="append">分</template>
				  </el-input>
				  <span class="note-margin">未付款，订单自动关闭</span>
				</el-form-item>
				<el-form-item label="正常订单超过：" prop="normalOrderOvertime">
				  <el-input v-model="orderSetting.normalOrderOvertime" class="input-width">
				    <template slot="append">分</template>
				  </el-input>
				  <span class="note-margin">未付款，订单自动关闭</span>
				</el-form-item>
				<el-form-item label="发货超过：" prop="confirmOvertime">
				  <el-input v-model="orderSetting.confirmOvertime" class="input-width">
				    <template slot="append">天</template>
				  </el-input>
				  <span class="note-margin">未收货，订单自动完成</span>
				</el-form-item>
				<el-form-item label="订单完成超过：" prop="finishOvertime">
				  <el-input v-model="orderSetting.finishOvertime" class="input-width">
				    <template slot="append">天</template>
				  </el-input>
				  <span class="note-margin">自动结束交易，不能申请售后</span>
				</el-form-item>
				<el-form-item label="订单完成超过：" prop="commentOvertime">
				  <el-input v-model="orderSetting.commentOvertime" class="input-width">
				    <template slot="append">天</template>
				  </el-input>
				  <span class="note-margin">自动五星好评</span>
				</el-form-item>
				<el-form-item>
				  <el-button
				    @click="confirm('orderSettingForm')"
				    type="primary">提交</el-button>
				</el-form-item>
			</el-form>
		</el-card>
		
	</div>
	
</template>

<script>
	export default {
		name: 'OrderSetting',
		data() {
		  return {
		 //    orderSetting: Object.assign({
			// 	flashOrderOvertime: '60',
			// }, defaultOrderSetting),
			orderSetting: {
				flashOrderOvertime: '60',
				normalOrderOvertime: '120',
				confirmOvertime: '15',
				finishOvertime:'7',
				commentOvertime:'7'
			},
		    rules: {
		      // flashOrderOvertime:[{validator: checkTime, trigger: 'blur' }],
		      // normalOrderOvertime:{validator: checkTime, trigger: 'blur' },
		      // confirmOvertime: {validator: checkTime, trigger: 'blur' },
		      // finishOvertime: {validator: checkTime, trigger: 'blur' },
		      // commentOvertime:{validator: checkTime, trigger: 'blur' }
		    }
		  }
		},
	}
</script>

<style lang="less">
	.input-width {
		width: 250px;
		margin-right: 15px;
	}
</style>
