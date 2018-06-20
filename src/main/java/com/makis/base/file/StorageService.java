package com.makis.base.file;

import com.makis.base.file.entities.Attachment;
import com.makis.base.file.mappers.AttachmentDao;
import com.makis.base.misc.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

/**
 * description：
 *
 * @author chenfuqian
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class StorageService {

    private final AttachmentDao attachmentDao;

    @Value("${upload.url}")
    private String uploadUrl;

    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    public StorageService(AttachmentDao attachmentDao) {
        this.attachmentDao = attachmentDao;
    }

    /**
     * 获取文件URL地址
     *
     * @param request 请求
     * @param id    文件ID
     * @return 文件URL地址
     */
    public String getUrl(String id) {
        if (StringUtils.isNullOrEmpty(id)) {
            return "";
        }

        Attachment attachment = attachmentDao.selectAttachment(id);
        if (attachment == null) {
            return "";
        }

        return String.format("%s/%s", attachment.getAttachmentServer(), attachment.getUrl());
    }
}
