package com.qiangzeng.learning.eduservice.controller.front;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qiangzeng.learning.common.util.ResponseResult;
import com.qiangzeng.learning.eduservice.entity.EduCourse;
import com.qiangzeng.learning.eduservice.entity.course.ChapteData;
import com.qiangzeng.learning.eduservice.entity.frontvo.CourseFront;
import com.qiangzeng.learning.eduservice.entity.frontvo.CourseWeb;
import com.qiangzeng.learning.eduservice.service.EduChapterService;
import com.qiangzeng.learning.eduservice.service.EduCourseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/eduservice/coursefront")
@CrossOrigin
public class CourseFrontController {

    @Autowired
    private EduCourseService courseService;

    @Autowired
    private EduChapterService chapterService;

    @Autowired
    //private OrdersClienl ordersClient;

    //1 条件查询带分页查询课程
    @PostMapping("getFrontCourseList/{page}/{limit}")
    public ResponseResult getFrontCourseList(@PathVariable long page, @PathVariable long limit,
                                             @RequestBody(required = false) CourseFront courseFrontVo) {
        Page<EduCourse> pageCourse = new Page<>(page,limit);
        //Map<String,Object> map = courseService.getCourseFrontList(pageCourse,courseFrontVo);
        //返回分页所有数据
        return ResponseResult.success();
    }

    //2 课程详情的方法
    @GetMapping("getFrontCourseInfo/{courseId}")
    public ResponseResult getFrontCourseInfo(@PathVariable String courseId, HttpServletRequest request) {
        //根据课程id，编写sql语句查询课程信息
        CourseWeb courseWebVo = courseService.getBaseCourseInfo(courseId);
        //根据课程id查询章节和小节
        //List<ChapteData> chapterVideoList = chapterService.getChapterVideoByCourseId(courseId);
        //根据课程id和用户id查询当前课程是否已经支付过了
        //boolean buyCourse = ordersClient.isBuyCourse(courseId, JwtUtils.getMemberIdByJwtToken(request));
        //return ResponseResult.ok().data("courseWebVo",courseWebVo).data("chapterVideoList",chapterVideoList).data("isBuy",buyCourse);
        return null;
    }

    //根据课程id查询课程信息
 //   @PostMapping("getCourseInfoOrder/{id}")
//    public CourseWebVoOrder getCourseInfoOrder(@PathVariable String id) {
//        CourseWeb courseInfo = courseService.getBaseCourseInfo(id);
//        CourseWebVoOrder courseWebVoOrder = new CourseWebVoOrder();
//        BeanUtils.copyProperties(courseInfo,courseWebVoOrder);
//        return courseWebVoOrder;
//    }
}












