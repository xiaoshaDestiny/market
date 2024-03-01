package com.rbxu.market.pushdata;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.rbxu.market.pushdata.dto.DiseaseTypeDTO;
import com.rbxu.market.pushdata.dto.PersonDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.math.NumberUtils;
import org.junit.Test;

import java.util.List;
import java.util.Set;

@Slf4j
public class GoodDoctorFacadeTest {

    private GoodDoctorFacade goodDoctorFacade = new GoodDoctorFacade();

    public static List<PersonDTO> getAllPerson() {
        List<PersonDTO> persons = Lists.newArrayList();
        String str = "11728126_梁康玉#12394141_许艳#12525417_段玉清#12594391_王贵友#12944835_苗家生#13044299_张顶华#13546644_杨欢#13613415_罗海虾#13623310_房业岐#13623310_房业岐#13650858_程兴美#137135433_急腹症#13717562_段诚惠#13717562_段诚惠#13717721_戚学贵#13717933_张艳#13717961_马羽春#13718154_王世高#13737462_彭国琼#13756599_付兆会#13756820_谭全富#13757246_苏应彪#13770519_肖邦慧#13773320_王芝兰#13773320_王芝兰#13805578_农永进#13823007_陈友廷#13828628_何永多#13856196_李珍美#13856587_蔡永珍#13880771_和宇鑫#13880771_和宇鑫#13881773_苏奎#13883377_谭正桂#13883377_谭正桂#13888794_余致原#13946866_黄兴能#13959742_夏智敏#13962589_管毓象#13981820_周清玲#13981865_张汶灿#13992844_刘琼芬#14096660_张自良#14162488_吴万彬#15238686_尹国良#6000511495_王绍德#6000513482_刘大广#6000514504_李换芝#6000516542_马加信#6000567763_沈啊都#6000594743_侍瑾鸿#6000611082_甄选美#6000612166_李桂美#6000638733_罗明琼#6000679644_毕龙粉#6000811910_徐惠琳#6000908551_黄佑琴#6000950550_杞学英#6000978990_普淑芳#6001079970_李英明#6001138932_溪文琼#6001163930_张伟#6001163930_张伟#6001205475_和雪莲#6001220977_罗仙浩#6001222712_杜洪碧#6001232136_张志新#6001344225_艾祖平#6001363234_杨会琼#6001374994_周会全#6001397547_母红英#6001412263_赵本树#6001418414_马鹃#6001420367_万训民#6001422539_杨成万#6001450547_李琼仙#6001454848_周建成#6001462201_李永贵#6001475214_玉儿总#6001488383_卢茄宾#6001506589_张义宽#6001508810_陈从实#6001525029_何菊珍#6001525029_何菊珍#6001526623_李德象#6001555779_杨健#6001561923_张娇#6001561923_张娇#6001562900_杨秀莲#6001577792_潘文红#6001584145_杨子贞#6001596864_唐正坤#6001599786_尧自华#6001609157_业丕伦#6001609157_业丕伦#6001613898_和占彬#6001629264_杨正洲#6001639015_陈麟#6001643908_白明生#6001643961_张菊#6001649955_刀美仙#6001656647_邹家恩#6001661526_唐华林#6001662085_李文翠#6001667156_王加义#6001672256_王运均#6001684702_陈家明#6001700281_付红飞#6001706479_普学芬#6001707345_张社军#6001708679_李润秀#6001713141_夏素梅#6001720335_赵丽勇#6001735273_李金春#6001746312_张有平#10692737_王立云#11063725_秦港#12453877_杨绍珍#12826114_余三春#13388085_陶荣祥#13669546_杨开全#13670019_郑少为#13691668_罗务干#13694774_柯忠明#137135433_耿加#13715926_杨虎#13881773_苏奎#13893641_李仁伟#140377828_王兴菊#140377828_王兴菊#14162104_雷宇#15137260_余惠珍#6000587383_饶曾才#6000642302_李绍鹏#6001193960_佘定莲#6001344225_艾祖平#6001363668_杨春莲#6001412263_赵本树#6001488120_钟德友#6001525138_蒋先顺#6001636767_常礼平#6001687555_徐学猛#6001744831_周绍军#6001744831_周绍军#10620522_杨晓霞#10663897_董凤兰#12132930_谷秀珍#13925856_余永恒#14011152_刘丹阳#6001163930_张伟#6001561609_岳标#11109229_李文跃#12293887_袁老珍#15120387_吴小提#10557605_朱春菊#11063725_秦港#11728126_梁康玉#12244202_陈知松#12884952_黄玉兰#13325252_俄奇布日#13326534_胡响亮#13485994_魏建伟#13509818_沈大禹#13593477_马永达#13613645_李二龙#13651453_蒋文献#13669669_岑涛#13688595_孟伟#13698643_范金#13700088_余关杨#13717107_高静#13717721_戚学贵#13719983_李军龙#13736408_赵艳会#13756820_谭全富#13787012_王礼兰#13800676_刘志光#13804641_刘柱江#13823007_陈友廷#13828628_何永多#13844059_耿春艳#13857343_何石囡#13877234_瞿小兰#13877868_王文坤#13881157_王跃武#13888055_阳建新#13888794_余致原#13946866_黄兴能#13958177_付廷怀#13958177_付廷怀#13962480_郭明秀#14010787_李发秀#14011152_刘丹阳#14096221_何宇明#6000539202_李吉华#6000567763_沈啊都#6000611082_甄选美#6000686303_万金兰#6000908551_黄佑琴#6000945504_蒋弟刚#6001041911_王牙长#6001236384_徐丽芳#6001251761_王永祥#6001251824_张明凤#6001318650_陆四妹#6001377817_宁艾玲#6001391394_倪琼芬#6001391394_倪琼芬#6001409388_解会琼#6001412263_赵本树#6001420367_万训民#6001430020_汪诗坤#6001450547_李琼仙#6001457848_李双#6001463793_罗才斌#6001475214_玉儿总#6001525138_蒋先顺#6001526623_李德象#6001528913_安龙军#6001553494_肖直勇#6001563017_罗必维#6001581359_曹玲玲#6001584145_杨子贞#6001599522_甘朝芳#6001599538_贺德珍#6001601990_陈雨#6001605770_何保得#6001625616_马得福#6001626122_曹有昌#6001626669_段蕊#6001626735_李福德#6001627538_李才刚#6001636767_常礼平#6001643013_周发荣#6001648385_杨太斌#6001651608_白福青#6001668492_杨涛#6001673809_李沙英#6001691846_王清玉#6001699907_陈迁怡#6001700281_付红飞#6001706058_李春红#6001706479_普学芬#6001743030_王应君#6001745536_李勇#6001745536_李勇#6001747715_刘国琳#6001747715_刘国琳#6001748605_冯竹芬#6001752233_杨春菊#6001755149_李秀松#6001756156_陈光利#6001756156_陈光利#6001764675_李美芬#6001768877_马登顺#97026680_巴依然别#10403818_孔云周#12188336_王菊英#12586166_朱自红#13103460_张玮#13320828_赵文同#13325252_俄奇布日#13657687_王乔富#13675520_龚平意#13881773_苏奎#6001193960_佘定莲#6001560580_罗和勋#6001610888_谭秀华#6001611298_钱老三#6001643961_张菊#6001654566_朱小文#6001671123_文浪芸#11291468_黄友仙#13691668_罗务干#6001429250_施汝发#6001561609_岳标#6001643908_白明生#13505586_党正林#13294106_张玉未#6001525138_蒋先顺#10188431_梁康玉#97026376_夏羽#11316879_戴鹏#12525417_段玉清#12574807_郭正发#12884952_黄玉兰#13237224_陈伟#13325252_俄奇布日#13452057_保贤梁#13608131_邓声芳#13619782_赵兴亮#13623461_刘应江#13623461_刘应江#13675553_皮鸿润#13675986_屠成江#13707278_韦昌贵#13719118_辛佳达#13719983_李军龙#13719983_李军龙#13756820_谭全富#13770519_肖邦慧#13783534_肖端#13787935_李绍华#13800676_刘志光#13828365_施永良#13841057_高贵强#13856196_李珍美#13876431_赵斌#13880495_李景怡#13880969_黄凤仙#13881773_苏奎#13882026_刘永荣#13882327_杨洪#13945889_罗忠秀#13988081_董石所#14000822_陈文仙#14096221_何宇明#14096221_何宇明#14162488_吴万彬#15514726_崔佳琪#15514726_崔佳琪#6000496222_张金仙#6000511495_王绍德#6000514182_马丽涛#6000516542_马加信#6000546363_张琼会#6000559237_陈继雄#6000573415_姚元仙#6000573415_姚元仙#6000576347_伏见岗#6000664089_毕旭#6000679000_李进#6001222712_杜洪碧#6001236384_徐丽芳#6001285527_曾昌富#6001397547_母红英#6001409388_解会琼#6001410669_代大飞#6001418131_周粉花#6001418414_马鹃#6001419956_吴通清#6001429250_施汝发#6001430020_汪诗坤#6001454848_周建成#6001508245_俞桂芬#6001512739_李中菊#6001523262_胡少学#6001528913_安龙军#6001545466_沈加发#6001548867_苏玉品#6001553494_肖直勇#6001557934_周正取#6001560580_罗和勋#6001562900_杨秀莲#6001563017_罗必维#6001564651_周兴进#6001582787_谷立明#6001599538_贺德珍#6001608697_杨溶#6001610107_李朝阳#6001610739_杨路顺#6001614794_余巡巡#6001620819_陈学会#6001626669_段蕊#6001630530_王如进#6001632868_肖帆#6001643013_周发荣#6001657957_徐云平#6001658053_袁坤#6001662085_李文翠#6001668492_杨涛#6001684702_陈家明#6001686684_王从英#6001703284_龙琼坤#6001708679_李润秀#6001735273_李金春#6001755149_李秀松#6001764675_李美芬#97026680_巴依然别#12348361_赵春梅#12586166_朱自红#13388085_陶荣祥#13669546_杨开全#13671370_薛国涛#13671370_薛国涛#13674776_刘满昌#13691668_罗务干#13705996_张楷桔#13715926_杨虎#13715935_张正博#13876490_杨茹英#6000868390_李美丽#6001525138_蒋先顺#6001550529_马琼梅#6001550529_马琼梅#10403818_孔云周#10403818_孔云周#10620522_杨晓霞#13806428_罗云付#14162488_吴万彬#14165475_黄垂明#6001385976_龚焕媛#6001429250_施汝发#12807032_朱勇刚#10826227_王东哲#13006486_王如胆#6001525138_蒋先顺#13500565_王俊生#13700984_李兆会#10126904_杨璐璐#97026290_余文乐#11063725_秦港#11316879_戴鹏#12467233_龙彩燕#13048925_王金#13523812_王秀珍#13608131_邓声芳#13613477_马桂芝#13619782_赵兴亮#13630831_赵洋#13630831_赵洋#13639365_唐正荣#13666834_龙梅#13675986_屠成江#13700088_余关杨#13707278_韦昌贵#13715399_贺晓龙#13715611_段诚惠#13716570_张青举#13717961_马羽春#13718154_王世高#13722732_周显能#13773656_孔国富#13773656_孔国富#13783534_肖端#13804641_刘柱江#13821310_李引贵#13828365_施永良#13841057_高贵强#13857343_何石囡#13877234_瞿小兰#13880028_唐永平#13880130_张何慈#13881522_张一枚#13883682_史靖阳#13935497_卢思伟#13962480_郭明秀#13976910_王廷宇#13981820_周清玲#13990083_孔令菊#14000822_陈文仙#14010787_李发秀#14011152_刘丹阳#14011152_刘丹阳#14011152_刘丹阳#14032316_周啟琴#14162488_吴万彬#14162488_吴万彬#14165475_黄垂明#15238686_尹国良#6000514182_马丽涛#6000516542_马加信#6000546363_张琼会#6000630566_祖娜#6000679000_李进#6000729889_李友生#6000729889_李友生#6000841656_王汝鹏#6000945504_蒋弟刚#6000950550_杞学英#6000950550_杞学英#6000978990_普淑芳#6001041911_王牙长#6001298637_栾创#6001374994_周会全#6001391394_倪琼芬#6001409255_蒋天政#6001409255_蒋天政#6001412344_马春琼#6001418195_陈中良#6001418195_陈中良#6001429250_施汝发#6001457848_李双#6001462201_李永贵#6001463793_罗才斌#6001465314_郑景文#6001479619_黄培贤#6001523262_胡少学#6001552436_尹春燕#6001564651_周兴进#6001565700_孙林#6001565700_孙林#6001569834_尹正科#6001577792_潘文红#6001582373_李奋箕#6001587390_陈礼妹#6001600182_张定秀#6001608697_杨溶#6001610739_杨路顺#6001614794_余巡巡#6001626669_段蕊#6001627917_朱天朝#6001630584_杨朝林#6001633001_何美查#6001636767_常礼平#6001638145_钱足勇#6001647578_刘丙江#6001648385_杨太斌#6001657059_李运芳#6001681925_杨洪#6001687555_徐学猛#6001691846_王清玉#6001695141_王显能#6001699907_陈迁怡#6001700281_付红飞#6001709916_王鑫#6001709916_王鑫#6001718870_阎桂秀#6001724045_朱平子#6001746312_张有平#6001752233_杨春菊#6001760149_翁学培#6001768877_马登顺#97023765_赵飞#11705622_罗燕#12348361_赵春梅#12725083_张国栋#12726615_陈贵华#12778195_张永正#12826114_余三春#13320828_赵文同#13346172_苏登远#13619825_彭建平#13624530_代利荣#13670019_郑少为#13705996_张楷桔#13893641_李仁伟#14015892_李嘉#14162104_雷宇#14162104_雷宇#6000479202_钱杭东#6001364318_赵凤仙#6001392109_赵磊#6001611298_钱老三#6001654566_朱小文#6001660989_汪利金#6001660989_汪利金#13329587_何锐#6001163930_张伟#6001549458_晋从华#6001565700_孙林#6001410669_代大飞#10826227_王东哲#11109229_李文跃#13700984_李兆会#12207020_许灯塔#12207020_许灯塔#97026744_舒梓轩#97026744_舒梓轩#11952021_李凤仙#12249730_张先群#13469861_江群梅#13469861_江群梅#13524645_他江婵#13546644_杨欢#13554060_赵腊英#13613001_吴坤雄#13619749_李海春#13623461_刘应江#13683722_张光毅#137135433_耿加#13717107_高静#13718208_李学军#13743423_毛胜红#13743423_毛胜红#13756533_汪啟学#13756599_付兆会#13771147_周凭#13772356_张廷燕#13782484_陈米花#13783534_肖端#13787012_王礼兰#13792197_唐云增#13792197_唐云增#13822666_曾得清#13828427_李道学#13841057_高贵强#13877868_王文坤#13880130_张何慈#13880495_李景怡#13882026_刘永荣#138850247_胡开帮#13950834_毕建凤#13955060_刘现福#13990083_孔令菊#13991846_李学文#140377791_向勇#14102301_黄顺稳#14162488_吴万彬#14165475_黄垂明#6000522822_彭飞#6000528485_胡建全#6000539202_李吉华#6000546363_张琼会#6000559237_陈继雄#6000630566_祖娜#6000664334_宁飞艳#6000679644_毕龙粉#6000686303_万金兰#6000701280_黄净萍#6000777250_张树珍#6000777250_张树珍#6000806977_何雪雁#6000811133_李兴珍#6000811133_李兴珍#6000950550_杞学英#6001205475_和雪莲#6001220977_罗仙浩#6001225753_郭勤仙#6001232136_张志新#6001232136_张志新#6001251761_王永祥#6001251824_张明凤#6001276032_肖波#6001363668_杨春莲#6001418131_周粉花#6001443275_张寿贤#6001443275_张寿贤#6001454874_梁谷存#6001458681_余田学#6001506589_张义宽#6001508245_俞桂芬#6001512739_李中菊#6001525114_周孟岩#6001545466_沈加发#6001548867_苏玉品#6001560580_罗和勋#6001569834_尹正科#6001577512_梁永庚#6001577849_余德荣#6001596864_唐正坤#6001601990_陈雨#6001611298_钱老三#6001626184_黄福万#6001643013_周发荣#6001643908_白明生#6001647578_刘丙江#6001649602_杨家艳#6001654566_朱小文#6001657059_李运芳#6001658053_袁坤#6001661526_唐华林#6001668141_陈永忠#6001669607_周妮#6001686187_万选恒#6001695832_李寿芬#6001706058_李春红#6001713839_游童武#6001718870_阎桂秀#6001748605_冯竹芬#6001757611_付声平#6001760149_翁学培#11705622_罗燕#12778195_张永正#13325252_俄奇布日#13346172_苏登远#13715926_杨虎#14015892_李嘉#14030264_王小荣#14162104_雷宇#6000479202_钱杭东#6000642302_李绍鹏#6000775916_陈绍琴#6001370783_文恩凤#6001392109_赵磊#6001525138_蒋先顺#6001599175_赵琦#6001668639_保乔焕#6001668639_保乔焕#12132930_谷秀珍#6001385976_龚焕媛#6001458896_李国贵#6001565700_孙林#12807032_朱勇刚#13782802_陆惠琼#6001410669_代大飞#11109229_李文跃#13294106_张玉未#97022541_马海日瓦#97026290_余文乐#11918813_胡培仙#12244202_陈知松#12435201_杨秀菊#12574807_郭正发#12594391_王贵友#12944835_苗家生#13452057_保贤梁#13524645_他江婵#13554758_耿忠文#13623461_刘应江#13624530_代利荣#13639365_唐正荣#13683722_张光毅#13688595_孟伟#13716570_张青举#13717933_张艳#13718208_李学军#13718550_申时玉#13719118_辛佳达#13769649_段猛#13805578_农永进#13805578_农永进#13876431_赵斌#13881157_王跃武#13883036_伯翠英#13888055_阳建新#13959742_夏智敏#13962589_管毓象#14011152_刘丹阳#14096660_张自良#14165475_黄垂明";
        List<String> items = Lists.newArrayList(str.split("#"));

        Set<String> ids = Sets.newHashSet();
        items.stream().forEach(key -> {
            String[] split = key.split("_");
            if (split.length == 2 && NumberUtils.isNumber(split[0])) {
                if (!ids.contains(split[0])) {
                    ids.add(split[0]);
                    persons.add(new PersonDTO(split[0], split[1]));
                }
            }
        });
        return persons;
    }

    @Test
    public void testPushData() {
        List<PersonDTO> persons = getAllPerson();
        List<DiseaseTypeDTO> bzList = Lists.newArrayList();

        bzList.add(new DiseaseTypeDTO(594, "神经外科", 2335, "颅脑外伤(包括创伤性脑挫裂伤、脑内血肿、硬膜下血肿、硬膜外血肿、颅骨骨折、头皮血肿、头皮裂伤等)"));
        bzList.add(new DiseaseTypeDTO(594, "神经外科", 2336, "颅脑肿瘤(包括脑膜瘤、胶质瘤、垂体瘤、颅咽管瘤、颅骨肿瘤头皮肿瘤等)"));
        bzList.add(new DiseaseTypeDTO(594, "神经外科", 2337, "脑血管病(包括高血压脑出血、脑梗死、脑动脉瘤、脑血管畸形、烟雾病、海绵状血管瘤、脑血管狭窄等)"));
        bzList.add(new DiseaseTypeDTO(594, "神经外科", 2338, "脊柱脊髓病变(包括脊膜瘤、神经鞘瘤、室管膜瘤、Chiari畸形、脊髓栓系综合征等)"));
        bzList.add(new DiseaseTypeDTO(594, "神经外科", 2339, "其他疾病"));


        int personIndex = 200;
        for (DiseaseTypeDTO diseaseTypeDTO : bzList) {
            for (int i = 0; i < 1; i++) {
                PersonDTO person = persons.get(personIndex);
                goodDoctorFacade.pushDiseaseType(person, diseaseTypeDTO);
                personIndex ++;
            }
        }
        log.info("index is {}", personIndex);
    }
}