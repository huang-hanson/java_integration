package com.hanson.demo.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName RedisKeyScannerService
 * @Description TODO
 * @date 2024/7/23 17:53
 **/
@Service
public class RedisKeyScannerService {

    @Resource(name = "keyScannerRedisTemplate")
    private RedisTemplate<String, Object> redisTemplate;

    public long countKeysByPattern(String pattern) {
        long count = 0;
        Cursor<byte[]> cursor = null;
        try {
            // 获取Redis连接
            RedisConnection connection = redisTemplate.getConnectionFactory().getConnection();
            // 使用SCAN命令，cursor初始化为ScanOptions.SCAN_POINTER_START（0L表示开始）
            cursor = connection.scan(ScanOptions.scanOptions().match(pattern).count(1000).build());
            // 遍历cursor中的key
            while (cursor.hasNext()) {
                cursor.next(); // 这里只是遍历，不实际使用key，所以调用next()即可
                count++;
            }
        } finally {
            // 关闭cursor（如果它没有被自动关闭的话）
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return count;
    }

    // 注意：上面的代码示例中，我们实际上并没有使用cursor.next()返回的key，
    // 只是用它来遍历并计数。如果你需要处理每个key，可以在while循环中处理它。

    // 另外，你可以调整ScanOptions中的count参数来优化性能，
    // 但请注意，这只是一个提示，Redis可能会返回更多或更少的元素。


    /**
     * 计算具有特定前缀的 key 的数量。
     *
     * @param prefix key 的前缀
     * @return 匹配的 key 数量
     */
    public long countKeysByPrefix(String prefix) {
        long count = 0;
        Cursor<byte[]> cursor = null;
        try {
            // 使用 RedisConnection 来执行 SCAN 命令
            RedisConnection connection = redisTemplate.getConnectionFactory().getConnection();
            // 初始化 SCAN 命令的选项，匹配前缀并设置每次扫描的 key 数
            ScanOptions options = ScanOptions.scanOptions().match(prefix + "*").count(1000).build();
            // 执行 SCAN 命令
            cursor = connection.scan(options);

            // 遍历 cursor
            while (cursor.hasNext()) {
                // 遍历每个 key（虽然这里只是计数，没有实际使用 key）
                cursor.next();
                count++;
            }
        } catch (Exception e) {
            // 处理异常
            e.printStackTrace();
            // 可以选择抛出运行时异常或进行其他错误处理
            throw new RuntimeException("Error counting keys in Redis", e);
        } finally {
            // 关闭 cursor（如果它没有被自动关闭）
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }

        return count;
    }
}