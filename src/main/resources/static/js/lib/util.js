layui.define((exports) => {
    /**
     * 函数去抖
     * @param func
     * @param delay
     * @returns {Function}
     */
    const debounce = (func, delay = 200) => {
        let timeout = null
        return () => {
            // 如果持续触发，那么就清除定时器，定时器的回调就不会执行
            clearTimeout(timeout)
            timeout = setTimeout(() => {
                func.apply(this, arguments)
            }, delay)
        }
    }

    /**
     * 函数节流
     * @param func
     * @param delay
     * @returns {Function}
     */
    const throttle = (func, delay = 500) => {
        let run = true
        return () => {
            // 如果开关关闭了，那就直接不执行下边的代码
            if (!run) {
                return
            }
            // 持续触发的话，run一直是false，就会停在上边的判断那里
            run = false
            setTimeout(() => {
                func.apply(this, arguments)
                // 定时器到时间之后，会把开关打开，我们的函数就会被执行
                run = true
            }, delay)
        }
    }

    const config = {
        scope: 'cache',
    }

    const util = {
        // 清空本地缓存
        removeAll: () => {
            layui.data(config.scope, null);
        },
        // 获取缓存
        getItem: (key) => {
            let item = layui.data(config.scope)[key];
            if (item) {
                return JSON.parse(item);
            }
        },
        // 缓存
        putItem: (key, value) => {
            layui.data(config.scope, {
                key: key,
                value: JSON.stringify(value)
            });
        },
    }
    exports('util', util)
})