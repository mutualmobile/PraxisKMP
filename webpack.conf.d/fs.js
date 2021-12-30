// project/webpack.conf.d/fs.js
config.resolve = {
    fallback: {
        "fs": false,
        "path": false,
        "crypto": false
    }
};