/**
 * 检验登录表单
 *
 * @returns {Boolean}
 */
function checkLoginForm() {
    if ($('#username').val() == "") {
        $('#message').html("用户名不能为空！");
        $('#dialog').css({visibility: "visible"});
        $('#username').focus();
        return false;
    }

    if ($('#password').val() == "") {
        $('#message').html("密码不能为空！");
        $('#dialog').css({visibility: "visible"});
        $('#password').focus();
        return false;
    }

    return true;
}

/**
 * 检验注册表单
 *
 * @returns {Boolean}
 */
function checkRegisterForm() {
    if ($('#username').val() == "") {
        $('#message').html("用户名不能为空！");
        $('#dialog').css({visibility: "visible"});
        $('#username').focus();
        return false;
    }

    if ($('#password').val() == "") {
        $('#message').html("密码不能为空！");
        $('#dialog').css({visibility: "visible"});
        $('#password').focus();
        return false;
    }

    var patrn = "/^(13[0-9]|14[0-9]|15[0-9]|18[0-9])\d{8}$/";
    if (!patrn.test($('#telephone').val())) {
        $('#message').html("非法手机号！");
        $('#dialog').css({visibility: "visible"});
        $('#telephone').focus();
        return false;
    }

    return true;
}


/**
 * 检查密码与确认密码是否一致
 *
 * @returns {boolean}
 */
function checkPassword() {
    if ($('#password').val() == "") {
        $('#message').html("密码不能为空！");
        $('#dialog').css({visibility: "visible"});
        $('#password').focus();
        return false;
    }

    if ($('#repassword').val() == "") {
        $('#message').html("确认密码不能为空！");
        $('#dialog').css({visibility: "visible"});
        $('#repassword').focus();
        return false;
    }

    if ($('#password').val() != $('#repassword').val()) {
        $('#message').html("密码与确认密码不一致！");
        $('#dialog').css({visibility: "visible"});
        return false;
    }

    return true;
}

/**
 * 检验类别名是否为空
 *
 * @returns {boolean}
 */
function checkCategoryName() {
    if ($('#categoryName').val() == "") {
        $('#message').html("类别名不能为空！");
        $('#dialog').css({visibility: "visible"});
        $('#categoryName').focus();
        return false;
    }
    return true;
}

/**
 * 检验商品名与单价是否为空
 *
 * @returns {boolean}
 */
function checkProduct() {
    if ($('#name').val() == "") {
        $('#message').html("商品名称不能为空！");
        $('#dialog').css({visibility: "visible"});
        $('#name').focus();
        return false;
    }
    if ($('#price').val() <= 0) {
        $('#message').html("商品单价必须为正数！");
        $('#dialog').css({visibility: "visible"});
        $('#price').focus();
        return false;
    }

    if ($('#imageFile').val() == "") {
        $('#message').html("必须设置商品图片！");
        $('#dialog').css({visibility: "visible"});
        return false;
    }
    return true;
}