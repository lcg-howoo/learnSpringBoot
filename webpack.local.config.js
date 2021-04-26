const path = require('path');
const { CleanWebpackPlugin } = require('clean-webpack-plugin');

module.exports = {
	devtool: 'inline-source-map',
	devServer: {
		contentBase: path.resolve(__dirname, 'dist', 'view'),
		inline: true,
		progress: false,
		hot: true,
		port: 3000,
		host: '0.0.0.0',
		disableHostCheck: true,
		publicPath: '/built/',
		headers: {
			'Access-Control-Allow-Origin': '*',
			'https': false,
		},
		watchOptions: {
			aggregateTimeout: 500,
			ignored: ['node_modules', 'src/main/java', 'webapp/WEB-INF'],
		},
	},
	node: {
		__dirname: false,
	},
	entry: './src/main/js/index.js',
	cache: true,
	mode: 'development',
	output: {
		path: path.resolve(__dirname, 'dist', 'view'),
		publicPath: 'http://localhost:3000/built/',
		filename: 'bundle.view.js',
	},
	resolve: {
		alias: {
		},
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
