package service;

import java.util.Set;

public interface PermissionService {
    boolean needInterceptor(String requestURI);

    Set<String> listPermissionURLs(String userName);

    Set<String> listPermissions(String userName);
}
