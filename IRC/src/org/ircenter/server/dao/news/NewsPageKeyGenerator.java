package org.ircenter.server.dao.news;

import com.googlecode.ehcache.annotations.key.CacheKeyGenerator;
import org.aopalliance.intercept.MethodInvocation;
import org.ircenter.server.bean.news.Key;

/**
 * User: Seledkov Kostyantyn
 * Date: 28.03.12
 * Time: 23:34
 */
public class NewsPageKeyGenerator implements CacheKeyGenerator<Key> {

    @Override
    public Key generateKey(MethodInvocation methodInvocation) {
        return new Key((Long) methodInvocation.getArguments()[0], (Integer) methodInvocation.getArguments()[1], (Integer) methodInvocation.getArguments()[2]);
    }

    @Override
    public Key generateKey(Object... objects) {
        return null;
    }
}
