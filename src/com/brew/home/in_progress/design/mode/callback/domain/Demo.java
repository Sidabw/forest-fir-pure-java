/**
 * Copyright (C), 2018-2019, zenki.ai
 * FileName: Demo
 * Author:   feiyi
 * Date:     2019/4/30 2:28 PM
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.brew.home.in_progress.design.mode.callback.domain;

import com.brew.home.in_progress.design.mode.callback.student.StudentSida;
import com.brew.home.in_progress.design.mode.callback.teacher.TeacherBrew;

/**
 * 〈一句话功能简述〉:
 * 〈〉
 *
 * @author feiyi
 * @date 2019/4/30
 * @since 1.0.0
 */
public class Demo {

    public static void main(String[] args) {

        TeacherBrew teacherBrew = new TeacherBrew(new StudentSida());
        teacherBrew.question();
    }
}
