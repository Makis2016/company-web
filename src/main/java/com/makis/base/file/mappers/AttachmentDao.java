package com.makis.base.file.mappers;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.makis.base.entities.Config;
import com.makis.base.file.entities.Attachment;
import org.apache.ibatis.annotations.Mapper;
import javax.persistence.Table;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Map;

/**
 * description：
 *
 * @author chenfuqian
 */
@Mapper
@Component
public interface AttachmentDao extends BaseMapper<Attachment> {
    class DaoProvider {
        public String selectAttachmentById(final Map<String, Object> param){
            String configTableName = Config.class.getAnnotation(Table.class).name();
            String attachmentTableName = Attachment.class.getAnnotation(Table.class).name();

            return new SQL(){{
                SELECT(" t1.id,t2.attachment_server");
                FROM(attachmentTableName + " t1");
                INNER_JOIN(configTableName + " t2 ON t1.config_id = t2.id");
                if (param.get("id") != null) {
                    WHERE(" t1.id =  #{id}");
                }
            }}.toString();
        }
    }

    /**
     * 根据附件表ID获取相关信息
     *
     * @param id 附件表ID
     * @return 附件
     */
    @SelectProvider(type = DaoProvider.class, method = "selectAttachmentById")
    Attachment selectAttachment(@Param("id") Serializable id);
}
