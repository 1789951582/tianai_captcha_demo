module.exports = {
	presets: [
		'@vue/cli-plugin-babel/preset'
	],
	plugins: [
		'@babel/plugin-proposal-class-properties', // 支持类属性
		'@babel/plugin-proposal-private-methods' // 支持私有方法
	]
}