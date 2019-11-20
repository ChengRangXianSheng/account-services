package com.soiiy.platform.utils.exception

/**
 *接口版本异常-需要更新客户端版本
 *@Author ChenRang
 *@Date  2019/11/16 20:39
 */
class HttpNotUpgradeException(): PlatformException(426, "需要更新！")