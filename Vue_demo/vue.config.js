const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  devServer: {
  	open: true, // npm run serve后自动打开页面
  	host: '::', // 匹配本机IP地址(默认是0.0.0.0)
  	port: 8088, // 开发服务器运行端口号
  	proxy: {
  		'/api': {
  			target: 'http://localhost:3223'
  		},
  
  	}
  }
})
