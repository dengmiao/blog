/**
 * 函数去抖
 * @param func
 * @param delay
 * @returns {Function}
 */
export const debounce = (func, delay = 200) => {
    let timeout = null
    return function () {
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
export const throttle = (func, delay = 500) => {
    let _self = func, timer,
        //记录是否是第一次执行的flag
        firstTime = true
    return () => {
        let args = arguments, _me = this

        //若是第一次，则直接执行
        if(firstTime) {

            _self.apply(_me, args)

            return firstTime = false

        }

        //定时器存在，说明有事件监听器在执行，直接返回
        if(timer) {
            return false
        }

        timer = setTimeout(() => {
            clearTimeout(timer)
            timer = null
            _self.apply(_me, args)
        }, delay)
    }
}