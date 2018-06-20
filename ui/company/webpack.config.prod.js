var path = require('path');
var params = require('./package.json');
var glob = require('globby');
var copyWebpackPlugin = require('copy-webpack-plugin');
var outputPath = '../../resources/company/';
var webpack = require('webpack');
var CompressionPlugin = require('compression-webpack-plugin');

module.exports = {
    // entry: getEntry(),
    output: {
        path: path.resolve(__dirname, outputPath),
        filename: 'js/[name].bundle.js'
    },
    module: {
        loaders: [
            {
                test: /\.js$/,
                loader: 'babel',
                query: {
                    presets: ["es2015"]
                },
                plugins: [
                    "transform-es3-property-literals",
                    "transform-es3-member-expression-literals",
                    "transform-es2015-modules-simple-commonjs",
                    "transform-runtime"
                ]
            },
            {
                test: /\.css$/,
                loader: 'style-loader!css-loader'
            },
            {
                test: /\.(gif|jpg|png|woff|svg|eot|ttf)\??.*$/,
                loader: 'url-loader?limit=50000&name=[path][name].[ext]'
            },
            {
                test: /\.(png|jpg)$/,
                loader: 'url-loader?name=img/[hash:8].[ext]&limit=1024' //hash:8
            },
            {
                test: /\.woff(\?v=\d+\.\d+\.\d+)?$/,
                loader: 'url?name=font/[name].[ext]&limit=10000&minetype=application/font-woff'
            },
            {
                test: /\.woff2(\?v=\d+\.\d+\.\d+)?$/,
                loader: 'url?name=font/[name].[ext]&limit=10&minetype=application/font-woff'
            },
            {
                test: /\.ttf(\?v=\d+\.\d+\.\d+)?$/,
                loader: 'url?name=font/[name].[ext]&limit=10&minetype=application/octet-stream'
            },
            {
                test: /\.eot(\?v=\d+\.\d+\.\d+)?$/,
                loader: 'file?name=font/[name].[eot]'
            },
            {
                test: /\.svg(\?v=\d+\.\d+\.\d+)?$/,
                loader: 'url?name=font/[name].[svg]&limit=10&minetype=image/svg+xml'
            }
        ]
    },
    plugins: [
        new copyWebpackPlugin([{
            from: path.resolve(__dirname, 'html/view'),
            to: path.resolve(__dirname, outputPath),
            type: 'dir',
            transform(content) {
                content = content.toString();
                content = content.replace(/<%=\s*baseurl\s*%>/g, params.baseurl);
                content = content.replace(/<%=\s*version\s*%>/g, params.version);
                return new Buffer(content, 'UTF-8');
            }
        }]),
        new copyWebpackPlugin([{
            from: path.resolve(__dirname, 'html/assets'),
            to: path.resolve(__dirname, outputPath + 'assets'),
            type: 'dir'
        }]),
        new copyWebpackPlugin([{
            from: path.resolve(__dirname, 'html/css'),
            to: path.resolve(__dirname, outputPath + 'css'),
            type: 'dir'
        }]),
        new copyWebpackPlugin([{
            from: path.resolve(__dirname, 'html/fonts'),
            to: path.resolve(__dirname, outputPath + 'fonts'),
            type: 'dir'
        }]),
        new copyWebpackPlugin([{
            from: path.resolve(__dirname, 'html/images'),
            to: path.resolve(__dirname, outputPath + 'images'),
            type: 'dir'
        }]),
        new copyWebpackPlugin([{
            from: path.resolve(__dirname, 'html/js'),
            to: path.resolve(__dirname, outputPath + 'js'),
            type: 'dir'
        }]),
        new copyWebpackPlugin([{
            from: path.resolve(__dirname, 'html/img'),
            to: path.resolve(__dirname, outputPath + 'img'),
            type: 'dir'
        }])
        // new CompressionPlugin({
        //     asset: '[path].gz[query]',
        //     algorithm: 'gzip',
        //     test: /\.(js|html)$/,
        //     threshold: 10240,
        //     minRatio: 0.8
        // }),
        // new webpack.optimize.UglifyJsPlugin({	//压缩代码
        //     compress: {
        //         warnings: false
        //     },
        //     except: ['$super', '$', 'exports', 'require']	//排除关键字
        // })
    ],
    // devtool: 'cheap-module-eval-source-map'
};