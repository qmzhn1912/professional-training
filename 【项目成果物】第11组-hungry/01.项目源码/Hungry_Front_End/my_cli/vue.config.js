const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  /*devServer:{
    proxy:{
      './api':{
        target: 'server-url',
        changeOrigin: true,
        pathRewrite: {'^/api' : '/api'}
      }
    }
  }*/
})
