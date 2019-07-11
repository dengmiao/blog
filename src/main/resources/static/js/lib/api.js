/**
 * api接口常量
 */

/**
 * 后台模块根
 * @type {string}
 */
const ADMIN_ROOT_PATH = ''

/**
 * 前台模块根
 * @type {string}
 */
const HOME_ROOT_PATH = ''

const api = {
    login: (data) => {
        return ajax(ADMIN_ROOT_PATH + '/login', data, 'POST', 'application/x-www-form-urlencoded')
    },
    test: {
        create: ({data}) => {
            return ajax(ADMIN_ROOT_PATH + '/test/', data, 'POST')
        },
        retrieve: (id) => {
            return ajax(ADMIN_ROOT_PATH + '/test/' + id)
        },
        update: (id, data) => {
            return ajax(ADMIN_ROOT_PATH + '/test/' + id, {data}, 'PUT')
        },
        delete: (id) => {
            return ajax(ADMIN_ROOT_PATH + '/test/' + id, {}, 'DELETE')
        },
    },
}