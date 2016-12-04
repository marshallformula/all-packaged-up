const path = require('path')
const webpack = require('webpack')
const CopyWebpackPlugin = require('copy-webpack-plugin')

const dest = path.resolve(__dirname, '../', 'resources/static')

module.exports = {
  entry: {
    app: ['./src/index.js']
  },

  output: {
    path: dest,
    filename: '[name].js'
  },

  module: {
    loaders: [
      {
        test: /\.(css|less)$/,
        loaders: ['style', 'css', 'less']
      },
      {
        test: /\.js$/,
        exclude: [/node_modules/, /elm-stuff/],
        loaders: ['babel']
      },
      {
        test: /\.html$/,
        exclude: /node_modules/,
        loader: 'file?name=[name].[ext]'
      },
      {
        test: /\.woff(2)?(\?v=[0-9]\.[0-9]\.[0-9])?$/,
        loader: 'url-loader?limit=10000&mimetype=application/font-woff',
      },
      {
        test: /\.(ttf|eot|svg|png|jpg|jpeg|gif)(\?v=[0-9]\.[0-9]\.[0-9])?$/,
        loader: 'file',
      },
    ],

    noParse: /\.elm$/
  },

  plugins: [
    new webpack.DefinePlugin({
      REST_URL :  JSON.stringify(process.env.REST_URL || "/api")
    }),

    new CopyWebpackPlugin([
      {
        from: 'assets',
        to: dest
      }
    ])
  ],

  devServer: {
    inline: true,
    stats: {colors: true},
    historyApiFallback: true,
    outputPath: dest
  }

}
