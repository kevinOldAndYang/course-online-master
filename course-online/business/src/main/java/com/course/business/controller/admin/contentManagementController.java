package com.course.business.controller.admin;

import com.course.server.dto.contentManagementDto;
import com.course.server.dto.PageDto;
import com.course.server.dto.ResponseDto;
import com.course.server.service.contentManagementService;
import com.course.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/admin/contentManagement")
public class contentManagementController {

    private static final Logger LOG = LoggerFactory.getLogger(contentManagementController.class);
    public static final String BUSINESS_NAME = "内容管理表";

    @Resource
    private contentManagementService contentManagementService;

    /**
     * 列表查询
     */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody PageDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        contentManagementService.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
     * 保存，id有值时更新，无值时新增
     */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody contentManagementDto contentManagementDto) {
        // 保存校验
        ValidatorUtil.length(contentManagementDto.getTitle(), "标题", 1, 64);
        ValidatorUtil.length(contentManagementDto.getIds(), "附件id集合", 1, 255);
        ValidatorUtil.length(contentManagementDto.getFirstColumn(), "一级栏目", 1, 64);
        ValidatorUtil.length(contentManagementDto.getSecondColumn(), "二级栏目", 1, 64);
        ValidatorUtil.length(contentManagementDto.getCreateBy(), "创建人", 1, 255);
        ValidatorUtil.length(contentManagementDto.getUpdateBy(), "更新人", 1, 255);

        ResponseDto responseDto = new ResponseDto();
        contentManagementService.save(contentManagementDto);
        responseDto.setContent(contentManagementDto);
        return responseDto;
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        contentManagementService.delete(id);
        return responseDto;
    }
}
