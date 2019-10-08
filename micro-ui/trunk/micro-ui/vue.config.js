const url = 'http://127.0.0.1:9999'
//const url = 'http://117.48.228.249:9999'
module.exports = {
    lintOnSave: true,
    productionSourceMap: false,
    chainWebpack: config => {
        // 忽略的打包文件
        config.externals({
            'vue': 'Vue',
            'vue-router': 'VueRouter',
            'vuex': 'Vuex',
            'axios': 'axios',
            'element-ui': 'ELEMENT'
        })
        const entry = config.entry('app')
        entry
            .add('babel-polyfill')
            .end()
        entry
            .add('classlist-polyfill')
            .end()
    },
    // 配置转发代理
    devServer: {
        proxy: {
            '/auth': {
                target: url,
                ws: true,
                pathRewrite: {
                    '^/auth': '/auth'
                }
            },
            '/admin': {
                target: url,
                ws: true,
                pathRewrite: {
                    '^/admin': '/admin'
                }
            },
            '/code': {
                target: url,
                ws: true,
                pathRewrite: {
                    '^/code': '/code'
                }
            },
            '/gen': {
                target: url,
                ws: true,
                pathRewrite: {
                    '^/gen': '/gen'
                }
            },
            '/daemon': {
                target: url,
                ws: true,
                pathRewrite: {
                    '^/daemon': '/daemon'
                }
            },
            '/tx': {
                target: url,
                ws: true,
                pathRewrite: {
                    '^/tx': '/tx'
                }
            },
            '/act': {
                target: url,
                ws: true,
                pathRewrite: {
                    '^/act': '/act'
                }
            },
            '/portal': {
                target: url,
                ws: true,
                pathRewrite: {
                    '^/portal': '/portal'
                }
            },
            '/job': {
                target: url,
                ws: true,
                pathRewrite: {
                    '^/job': '/job'
                }
            },
            '/rest': {
                target: url,
                ws: true,
                pathRewrite: {
                    '^/rest': '/rest'
                }
            },
            '/finest': {
                target: "http://68.26.19.197:8380",
                changeOrigin: true,
            },
            '/v1': {
                target: 'https://68.26.19.197:8343',
                changeOrigin: true,
            },
            '/cas': {
                target: url,
                changeOrigin: true,
                pathRewrite: {
                    '^/cas': '/cas'
                }
            },
        }
    }
}
