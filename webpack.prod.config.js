const path = require('path');
const { CleanWebpackPlugin } = require('clean-webpack-plugin');
const TerserPlugin = require('terser-webpack-plugin');

module.exports = {
	// optimization: {
	// 	minimize: true,
	// 	minimizer: [
	// 		new TerserPlugin({
	// 			parallel: true,
	// 			terserOptions: {
	// 				warnings: false,
	// 				compress: {
	// 					warnings: false,
	// 					unused: true,
	// 				},
	// 				ecma: 6,
	// 				mangle: true,
	// 				unused: true,
	// 			},
	// 		}),
	// 	],
	// },
	devtool: 'source-map',
	node: {
		__dirname: false,
	},
	entry: './src/main/js/index.js',
	cache: false,
	mode: 'production',
	output: {
		path: path.resolve(__dirname, 'src', 'main', 'webapp', 'built'),
		filename: 'bundle.view.js',
	},
	resolve: {
		alias: {},
		plugins: [],
	},
	module: {
		rules: [
			{
				test: /\.js$/,
				exclude: /(node_modules)/,
				include: [path.resolve(__dirname, 'src', 'main', 'js')],
				use: {
					loader: 'babel-loader',
					options: {
						presets: ['@babel/preset-env', '@babel/preset-react'],
					},
				},
			},
		],
	},
	plugins: [new CleanWebpackPlugin()],
};
