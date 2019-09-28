package cn.mj.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class ExcelListener extends AnalysisEventListener {

    public List<List<Object>> datas = new ArrayList<>();

    public List<List<Object>> getDatas() {
        return datas;
    }

    public void setDatas(List<List<Object>> datas) {
        this.datas = datas;
    }

    @Override
    public void invoke(Object object, AnalysisContext context) {
        List<Object> stringList = (List<Object>) object;
        datas.add(stringList);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        //解析结束销毁不用的资源
        // datas.clear();
    }
}