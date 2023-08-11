const { defineConfig } = require('@vue/cli-service')
const BundleAnalyzerPlugin = require('webpack-bundle-analyzer')
    .BundleAnalyzerPlugin;

module.exports = defineConfig({

  outputDir: '../src/main/resources/static',

  transpileDependencies: true,

  configureWebpack: {
    plugins: [new BundleAnalyzerPlugin()],

  }
})
