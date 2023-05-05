package com.course.server.service;

import com.course.server.domain.contentManagement;
import com.course.server.domain.contentManagementExample;
import com.course.server.dto.contentManagementDto;
import com.course.server.dto.PageDto;
import com.course.server.mapper.contentManagementMapper;
import com.course.server.util.CopyUtil;
import com.course.server.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Date;

@Service
public class contentManagementService {

    @Resource
    private contentManagementMapper contentManagementMapper;

    /**
     * 列表查询
     */
    public void list(PageDto pageDto) {
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        contentManagementExample contentManagementExample = new contentManagementExample();
        List<contentManagement> contentManagementList = contentManagementMapper.selectByExample(contentManagementExample);
        PageInfo<contentManagement> pageInfo = new PageInfo<>(contentManagementList);
        pageDto.setTotal(pageInfo.getTotal());
        List<contentManagementDto> contentManagementDtoList = CopyUtil.copyList(contentManagementList, contentManagementDto.class);
        pageDto.setList(contentManagementDtoList);
    }

    /**
     * 保存，id有值时更新，无值时新增
     */
    public void save(contentManagementDto contentManagementDto) {
        contentManagement contentManagement = CopyUtil.copy(contentManagementDto, contentManagement.class);
        if (StringUtils.isEmpty(contentManagementDto.getId())) {
            this.insert(contentManagement);
        } else {
            this.update(contentManagement);
        }
    }

    /**
     * 新增
     */
    private void insert(contentManagement contentManagement) {
        Date now = new Date();
        contentManagement.setId(UuidUtil.getShortUuid());
        contentManagementMapper.insert(contentManagement);
    }

    /**
     * 更新
     */
    private void update(contentManagement contentManagement) {
        contentManagementMapper.updateByPrimaryKey(contentManagement);
    }

    /**
     * 删除
     */
    public void delete(String id) {
        contentManagementMapper.deleteByPrimaryKey(id);
    }
}
