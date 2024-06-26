/**
 * Copyright (C), 2018-2019, zenki.ai
 * FileName: TeacherBrew
 * Author:   feiyi
 * Date:     2019/4/30 2:23 PM
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.brew.home.in_progress.design.mode.callback.teacher;

import com.brew.home.in_progress.design.mode.callback.student.IStudent;

/**
 * 〈一句话功能简述〉:
 * 〈〉
 *
 * @author feiyi
 * @date 2019/4/30
 * @since 1.0.0
 */
public class TeacherBrew implements ITeacherCallback {


    private IStudent student;

    public TeacherBrew(IStudent student) {
        this.student = student;
    }

    public void question() {
        System.out.println(this.getClass().getName() + "    开始提问");
        student.resolveQuestions(this);
    }

    @Override
    public void tellAnswer(Object answer) {
        System.out.println(this.getClass().getName() + "    收到了：" + answer);
    }
}
