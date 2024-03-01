package com.hanson.ik;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

import java.io.IOException;
import java.io.StringReader;

@SpringBootTest
class IkAnalyzerDemoApplicationTests {

    @Test
    void test() {
        String text = "我喜欢使用IK分词器进行中文分词。";

        try (StringReader reader = new StringReader(text)){
            IKSegmenter ikSegmenter = new IKSegmenter(reader, true);
            Lexeme lexeme;
            while ((lexeme = ikSegmenter.next()) != null){
                System.out.println(lexeme.getLexemeText());
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
