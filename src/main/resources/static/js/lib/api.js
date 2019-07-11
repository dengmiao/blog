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
        ajax(ADMIN_ROOT_PATH + '/login', data, 'POST')
    },
    test: {
        create: ({data}) => {
            ajax(ADMIN_ROOT_PATH + '/test/', data, 'POST')
        },
        retrieve: (id) => {
            ajax(ADMIN_ROOT_PATH + '/test/' + id)
        },
        update: (id, data) => {
            ajax(ADMIN_ROOT_PATH + '/test/' + id, {data}, 'PUT')
        },
        delete: (id) => {
            ajax(ADMIN_ROOT_PATH + '/test/' + id, {}, 'DELETE')
        },
    },
}