layui.define((exports) => {
    /**
     * 函数去抖
     * @param func
     * @param delay
     * @param options
     * @returns {_debounce}
     */
    const debounce = (func, delay = 200, options = {
        leading: true, // 是否在进入时立即执行一次
        trailing: true, // 是否在事件触发结束后额外再触发一次，原理是利用定时器，如果在规定时间内再次触发事件会将上次的定时器清除，即不会执行函数并重新设置一个新的定时器，直到超过规定时间自动触发定时器中的函数
        context: null,
    }) => {
        let timer
        const _debounce = (...args) => {
            if(timer) {
                clearTimeout(timer)
            }
            if(options.leading && ! timer) {
                timer = setTimeout(null, delay)
                func.apply(options.context, args)
            }
            else if(options.trailing) {
                timer = setTimeout(() => {
                    func.apply(options.context, args)
                    timer = null
                }, delay)
            }
        }
        // 清除内部的计数器
        _debounce.cancel = () => {
            clearTimeout(timer)
            timer = null
        }
        return _debounce
        /*return () => {
            // 如果持续触发，那么就清除定时器，定时器的回调就不会执行
            clearTimeout(timer)
            timer = setTimeout(() => {
                func.apply(this, arguments)
            }, delay)
        }*/
    }

    /**
     * 函数节流
     * @param func
     * @param delay
     * @returns {Function}
     */
    const throttle = (func, delay = 500, options = {
        leading: true,
        trailing: false,
        context: null
    }) => {
        let previous = new Date(0).getTime()
        let timer
        const _throttle = (...args) => {
            let now = new Date().getTime()

            if(!options.leading) {
                if(timer) {
                    return
                }
                timer = setTimeout(() => {
                    timer = null
                    func.apply(options.context, args)
                }, delay)
            } else if(now - previous > delay) {
                func.apply(options.context, args)
                previous = now
            } else if(options.trailing) {
                clearTimeout(timer)
                timer = setTimeout(() => {
                    func.apply(options.context, args)
                }, delay)
            }
        }

        _throttle.cancel = () => {
            previous = 0
            clearTimeout(timer)
            timer = null
        }
        return _throttle
        /*let run = true
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
        }*/
    }

    /**
     * 柯里化
     * @param fn
     * @param args
     * @returns {Function}
     */
    const currying = (fn, ...args) => {
        let all = args || []
        length = fn.length
        return (...rest) => {
            let _args = all
            _args.push(...rest)
            if(rest.length === 0){
                return fn.call(this, args)
            }else{
                return currying.apply(this, fn, ..._args)
            }
        }
    }

    /**
     * 单例
     * @param func
     * @returns {*}
     */
    const proxy = (func) => {
        let instance
        let handler = {
            construct(target, args) {
                if(!instance) {
                    instance = Reflect.construct(func, args)
                }
                return instance
            }
        }
        return new proxy(func, handler)
    }

    /**
     *
     * @param asyncFunc
     * @returns {function(...[*]): Promise<any>}
     */
    const promisify = (asyncFunc) => {
        return (...args) => {
            return new Promise((resolve, reject) => {
                args.push(function callback(err, ...values) {
                    if(err) {
                        return reject(err)
                    }
                    return resolve(...values)
                })
                asyncFunc.call(this, ...args)
            })
        }
    }

    /**
     *
     * @param promise
     * @returns {Promise<*[]>}
     */
    const errorCaptured = async (promise) => {
        try {
            let res = await promise
            return [null, res]
        } catch (e) {
            return [e, null]
        }
    }

    const config = {
        scope: 'cache',
    }

    const toolKit = {
        storage: {
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
        },
    }
    exports('toolKit', toolKit)
})