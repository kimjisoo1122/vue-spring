const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,

  outputDir: '../src/main/resources/static/vue/',
  indexPath: "index.html",
  publicPath: process.env.NODE_ENV === 'production'
      ? '/vue/'
      : '/',

  devServer: {
    proxy: {
      '/': {
        target: 'http://localhost:8080',
        changeOrigin: true,
        ws: false
      }
    }
  }
})
