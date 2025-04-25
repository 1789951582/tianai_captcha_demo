<template>
	<div id="login-div">
		<!-- 装载验证码的DIV -->
		<div id="captcha-box" ref="captchaBox" v-show="captchaBoxShow"></div>
		<h1>用户登录</h1>
		<div class="input">
			<span class="input-left">用户名</span>
			xxxxx
		</div>
		<div class="input">
			<span class="input-left">密码</span>
			xxxxx
		</div>
		<div class="login-btn" @click="loginBtn">登录(图标点选)</div>
	</div>
</template>

<!-- <script src="/tac/js/tac.min.js"></script> -->
<script>
	import TAC from "../../public/tac/js/tac.min.js"
	import "../../public/tac/css/tac.css"
	import axios from "axios"
	export default {
		name: 'LoginDiv',
		data() {
			return{
				token:'123456',
				captcha:undefined,
				captchaBoxShow:false
			}
		},
		mounted() {
			this.captchaInit()
		},
		methods:{
			captchaInit(){
				const config={
					bindEl: this.$refs.captchaBox,//绑定div
					validCaptcha:this.sendAnswer,//验证触发的回调(鼠标拖动后松开)
					btnRefreshFun:this.btnRefreshFun,//刷新触发的回调
					btnCloseFun:this.btnCloseFun//点击关闭触发的回调
				}
				let style = {
					bgUrl:null,//背景Url
					logoUrl: null//logoUrl
				}
				this.captcha=new TAC(config,style)
			},
			sendAnswer(id,data,c,tac){
				axios.post("/api/passport/captcha",{
					token:this.token,//你需要携带的额外参数
					//用于验证的参数
					codeId: id,
					datas:data
				},{
					'Content-Type': 'application/json;charset=UTF-8',
				}).then(res=>{
					if(res.status==200){
						if(res.data.data){
							const useTimes = (data.stopTime - data.startTime) / 1000;
							c.showTips(`验证成功,耗时${useTimes}秒`,1)//消息提示方法
							setTimeout(()=>{
								tac.destroyWindow()//销毁验证码方法
							},3000)
							
						}else {
							c.showTips("验证失败，请重新尝试!", 0);
							setTimeout(()=>{
								tac.btnRefreshFun()//触发刷新
							},2000)
						}
					}else{
						c.showTips("验证码被黑洞吸走了！", 0);
						setTimeout(()=>{
							tac.btnRefreshFun()//触发刷新
						},2000)
					}
				})
			},
			getCaptcha(){
				return axios.post('/api/passport/get_captcha',{
					token:this.token
				},{
					'Content-Type': 'application/json;charset=UTF-8',
				})
				
			},
			btnRefreshFun(tac){
				this.getCaptcha().then(res=>{
					if(res.data.status==200){
						tac.pushData(res.data.data)
					}
				})
			},
			loginBtn(){
				this.getCaptcha().then(res=>{
					if(res.data.status==200){
						this.captcha.init();
						this.captchaBoxShow=true
						this.captcha.pushData(res.data.data)
					}
				})
			},
			btnCloseFun(){
				this.captchaBoxShow=false
			}
		}
	}
</script>

<style scoped>
	#login-div {
		width: 500px;
		height: 500px;
		border: 1px solid #ff5d39;
		position: relative;
	}

	h1 {
		text-align: center;
	}

	.input {
		height: 50px;
		width: 100%;
		border: 1px solid #ccc;
		border-radius: 6px;
		margin: 20px auto;
		color: #ccc;
		line-height: 50px;
		text-align: left;
	}

	.input-left {
		border-right: 1px solid #ccc;
		text-align: center;
		width: 100px;
		display: inline-block;
	}

	.login-btn {
		/*margin: 0 auto;*/
		display: inline-block;
		width: 160px;
		height: 50px;
		background-color: #4BC065;
		color: #fff;
		line-height: 50px;
		text-align: center;
		border-radius: 6px;
	}

	#captcha-box {
		position: absolute;
		left: 78px;
		top: 83px;
	}
</style>