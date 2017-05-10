/**
 * Created by zhangbokang on 2017/5/10.
 */
/**
 * 使用实例
 */

var data = {
    OK: 200,
    FAIL: 500,
    ENTRY: {
        FA_TOKEN_INVALID: 1001,
        FA_TOKEN_EXPIRE: 1002,
        FA_USER_NOT_EXIST: 1003
    },
    GATE: {
        FA_NO_SERVER_AVAILABLE: 2001
    },
    CHAT: {
        FA_CHANNEL_CREATE: 3001,
        FA_CHANNEL_NOT_EXIST: 3002,
        FA_UNKNOWN_CONNECTOR: 3003,
        FA_USER_NOT_ONLINE: 3004
    }
};

var json = new Json();

console.log("获取Json中节点");
console.log(json.get(data, "OK")); // 200
console.log(json.get(data, "ENTRY", "FA_TOKEN_INVALID")); 	// 1001
console.log(json.get(data, "TEST", "获取没有的节点")); 		// false 没有的节点返回 false

console.log("设置Json中节点");
console.log(json.set(data, "ENTRY", "FA_TOKEN_INVALID", 1234));   // true 	设置成功
console.log(json.get(data, "ENTRY", "FA_TOKEN_INVALID")); 		  // 1234  	获取刚设置的节点
console.log(json.set(data, "ENTRY", "测试设置没有的节点", 1234)); // false 	设置失败

console.log("创建新的Json节点");
var prods = {
    'name': 'Alan',
    'grade': {
        'Chinese': 120,
        'math': 130,
        'competition': {
            'NOI': 'First prize'
        }
    }
};
console.log(json.create(prods, 'create', 'hello', 'test', 120)); 	 // true
console.log(json.create(prods, 'create', 'hello', 'test2', '通过')); // true

console.log("格式化输出节点");
json.print_r(prods);

console.log("删除节点");
console.log(json.delete(prods, 'grade', 'math')); 			// true
console.log(json.delete(prods, 'grade', 'competition')); 	// true
console.log(json.delete(prods, 'grade', '删除没有的节点')); // false
json.print_r(prods);