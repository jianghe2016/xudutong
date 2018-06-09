package com.xdt.xudutong.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017\12\19 0019.
 */

public class ViploadUserInfo implements Serializable{

    /**
     * flag : 1
     * code : R00001
     * desc : 获取用户详细信息成功
      content : {"data":{"username":"15993651314","nickname":"高雅9","gender":"女","birthday":"1993-11-02","mobile":"15993651314","id":374,"headImg":"/9j/4AAQSkZJRgABAQAASABIAAD/4QBYRXhpZgAATU0AKgAAAAgAAgESAAMAAAAB\r\nAAEAAIdpAAQAAAABAAAAJgAAAAAAA6ABAAMAAAABAAEAAKACAAQAAAABAAAC7qAD\r\nAAQAAAABAAAC2AAAAAD/7QA4UGhvdG9zaG9wIDMuMAA4QklNBAQAAAAAAAA4QklN\r\nBCUAAAAAABDUHYzZjwCyBOmACZjs+EJ+/8AAEQgC2ALuAwEiAAIRAQMRAf/EAB8A\r\nAAEFAQEBAQEBAAAAAAAAAAABAgMEBQYHCAkKC//EALUQAAIBAwMCBAMFBQQEAAAB\r\nfQECAwAEEQUSITFBBhNRYQcicRQygZGhCCNCscEVUtHwJDNicoIJChYXGBkaJSYn\r\nKCkqNDU2Nzg5OkNERUZHSElKU1RVVldYWVpjZGVmZ2hpanN0dXZ3eHl6g4SFhoeI\r\niYqSk5SVlpeYmZqio6Slpqeoqaqys7S1tre4ubrCw8TFxsfIycrS09TV1tfY2drh\r\n4uPk5ebn6Onq8fLz9PX29/j5+v/EAB8BAAMBAQEBAQEBAQEAAAAAAAABAgMEBQYH\r\nCAkKC//EALURAAIBAgQEAwQHBQQEAAECdwABAgMRBAUhMQYSQVEHYXETIjKBCBRC\r\nkaGxwQkjM1LwFWJy0QoWJDThJfEXGBkaJicoKSo1Njc4OTpDREVGR0hJSlNUVVZX\r\nWFlaY2RlZmdoaWpzdHV2d3h5eoKDhIWGh4iJipKTlJWWl5iZmqKjpKWmp6ipqrKz\r\ntLW2t7i5usLDxMXGx8jJytLT1NXW19jZ2uLj5OXm5+jp6vLz9PX29/j5+v/bAEMA\r\nGxsbGxsbLxsbL0IvLy9CWUJCQkJZcFlZWVlZcIhwcHBwcHCIiIiIiIiIiKOjo6Oj\r\no76+vr6+1dXV1dXV1dXV1f/bAEMBISMjNjI2XTIyXd+XfJff39/f39/f39/f39/f\r\n39/f39/f39/f39/f39/f39/f39/f39/f39/f39/f39/f39/f3//dAAQAL//aAAwD\r\nAQACEQMRAD8Aw6KKKDqCiiigAooooAKKKWgBKKKKACiiikAUUUUxhS0lFABRRRQA\r\ntJS0lAC0UUlAC0lFLQAUUUUgCiiigApaKSgBc0UlFAC80lL1pKACilpKACiiimAU\r\ntJQKQCiiikoAKKWigAoopKAFpDRS0AJS0lLTAKKOtFIAoopR0oASlpOlFAC4pKWk\r\noAKKKXHegBKMUUooAKKOaKAEopaKAEopcUhoAWkoFLQMOtHNFFAg+lFFFAwoxSUt\r\nAhaSiigBc0UUlABRRRQAtFJRQMKWkooELRzSUpoASiiigBaM0lFAC5o5pKWgD//Q\r\nw6KSloOoKKKKQwooopgFFFFABRRRSEFLSUUDFoopKACiiigAooopgFFFFAC0UlLS\r\nAKSlopgFFFFABRRS0gEooooAKKWigBKKKKAFpKKKYBRRRSAKKKKYBRQKWkAc0UUU\r\nAJRS9qTrQAtFFFABQaSigB3akpc0lABRRSUALRRRQAppKKKACiiigBaSiloASijF\r\nLigYUUc0UCFpKKSgBaPeilzQAlFGKKAFoxSUZoAWkFJSigBaTFApaAD2pKKKADNF\r\nLxSd6ACiiigYUdKOaKACj6UUCgQc0fSlpPegA96WiigBOaUe9FNoA//RwqWiig6g\r\nooooAKKKWgYlFFFAgooooAKKKKBhS0lLQAlFFFAC0UlLmgBKWikpALRRRQAlFLSU\r\nwFooopAFFFFMAoopeKQBSGlpKACiiloASlpKWgApKKKACiiimAUUUUgCloooAKSl\r\nooAKKDRQAlFFFABS0YpKYC0A0UUgCjmiigAoooNABS5pKKADtxS0lFABS5pKKBi0\r\nUlL1oEFJ3paKBhRRS8igQlGaKKADk80lKKWgBKKKKAClpKXigAxSUlKKACigmigB\r\naSiigAopfpRQAlFFLQAlFL0pPrQAUuKSigBaKMiigD//0sMUUUUjqCiiimAUtJS0\r\nDEopc0UAJS0lFAC0lFFABRS0lABRRRQAUUUUALSUtFIAoo5opgFJRS0AJRS0UAFF\r\nFFIAooooAKKKWgApKKWgBKXNFJQAtFJRQAUUUUAFFFFMApaSikAtFFFAC0GkooAS\r\niil70AFFLSUAApTQKQ0AFFFFAC0UlFAB0oNFFAC0ZoooAKKDSUAFLSUtABSUtH0o\r\nAWikozQAtJRR70DCiijtQAUUUUCCg0daMUAJS0UlAC0UZo5oAOaWkooAKUUlLQAU\r\nUUUALSGj3pKACig0UAFLSdKM0Af/08Oiiig6gpaSigAooooGFFFFABRRRSAKKKWm\r\nAlLSUtIBKKKWmAlFFFABRRRSAWiiimAUUUUAFFFFIAooooAKKKKYBRRRSAWkpaSg\r\nApaSimAtIaKKACiiikAUtJS0AFFFFACUtFJQAtGKKKACkpcUUAFFFFABS5pKKACi\r\niigAoFLSUAFFFFAC4oozRQAUUc0UAFFFFABRRS80AJQc0UUAFLSUUAFFAo4oGJS0\r\nUtAhOaKCe1LQAlHFGKSgYopcUmaCaBC0lLSYoAKWkooAKSlpaAE+tFFLQAlFLQcU\r\nAJRRRzQB/9TDpKKWg6gooopDCiiimIKKKKQBRRRTGFFLRSAKKSlpgJRRS0AJRRRQ\r\nAUtFFABRRSUgFooopgFFFFIApKWimAlLRRQAUtJS0gCikooAKKDRTAKKKWkAlFLR\r\nQAUUUcUAJS0UlAC5pKKKACloooAWkoooAKKKKACikpaACikpaACk+lLS8UAJS0lF\r\nABS+1JRQA6ikooAOtFLxTaAFopKWgAo6UUUAFFHSigYUcUUUAFFFFAg4o4pKcKAE\r\noxRRQAUUUUAFFFFABRyOKMZo6UAGaKKOtABS5pKWgAooFFABRQKDQB//1cOiiig6\r\ngoxRRQAUUUUgCiiimMKKKKQBRRRQAtJS0lABRRRQAUUUtMAooopAJS0lLQAUUtJQ\r\nAUUUUAFFFFMAooooAKKXpSdqQBRR0ooAKKKKAFFFJ9KKACiiigBaKKKACkpaKAEo\r\npaKACilpKACiiigAopKWgAoooFABRS5pKACilpMUAFFFFABRRRQMKKKKACiiloAS\r\nlox3pKBC0UlLmgBeDSUUZoAMelFLSdaAEpaMUUAFFJS4NABSUtFACUtFJQAtFFJQ\r\nAv0ooGaKACiiigBfrRSZpc0AJS0UlAC4opKXNAH/1sOiiig6gpKWigAooooGFFFF\r\nABS0lFABS0UUgCikpaYCUUUUAFFFKKACiiikAUUUUwCiiikAUlLRQAlLRRQAUUUt\r\nMBKKBRSAKKKWgBKU0lFABRRiigAooooAKWkpaAClpKWgBMelFLQaAEooozQAUUtF\r\nACUUGigAooooAKKKKAClzSUUALSUYooAKKKKAClpBS0DEpaKKBBRxSUUAFFLRQAU\r\nlFLQAAUUfSigAooooAKKSj6UAOpKKKACjnvSUUALSGilxQAnWl4opKAFopaSgAoo\r\npKAFooooAXikoooA/9fDooooOsKKKKBBRRRQAUUUUDCiiigApaKSgBaSiikAUUUU\r\nwClpKKAClpKWgA4ooopAFFFFACUUUtMApKKWgAooopAFFFFMApaSikAUUUUALSUt\r\nFACUUtJQAUUUUwClopKQDs0UlFABSU6m4oAXNAoFFAC0lHNKKAE6UUuc0lAC0lLm\r\nkoAMUUUUALSUv1oxQAlHNLSUAFLiij6UAFFJRQAtHtSUtACUtFJQAUooooABRRmj\r\nNABRSUcUALQaSnUAJSUtLQAlH1o4pKAFopKKAFooooAKKKKADmilooATmijNFAB0\r\no+lGaKAP/9DDpKWig6gooooAKKKKACiiigAooooGFFFFABRRS0gEpaKSgAoopaAC\r\niiimAlFFLQAUUUUgCiiigAooopgFGKKKQBRRRQAUUUUwCiiloASiilpAFJS0lMAo\r\npaSkAtIKWkoAWijiigAooNLQAlFBooAKKKKACiiigBaKKKAEpe1FFABRSZpaACkp\r\nfaigBKUUlLQAlFFFAC0ZoooAOKSlpKAFopKKAFozRRQAlLwaSl7UABooozQAUUUl\r\nABRS0UDEpaSloEFFLikoAKOKOlFAC0ZpKOlABRRml7UAJRRRQB//0cOiiig6gooo\r\noAKKKKACiiikMKKKKACiiimAUtJS0gEopaOKAEpaKKYBRRRSAKKSlpgKFJGQDxRU\r\n9tcNA/qp6j1q/LZJOvm2x6/w0ieaz1MiinujI21xg02goSiiimMKKWkoAKKKKBBR\r\nRRQAUtJS0gEpaSigBaSlpKAFpKWkoAWiikoAWiikoAKWiigBaSiigBKWiigAoozS\r\n0AJS4opKAClpKKAClpKKADpRRRQAooopKAClpKWgAooooAKKKSgAooooAKWkpaAC\r\nkpaPpQAlLSUUALSUtFAB2opKMUALxRSUUAOoptKKAD60UtJQAdaXFJRmgAxijOKO\r\naM0AHFFFFAH/0sOiiig6gooooAKKKKAEpaKKBhRRRQAUUUUALRRRSAWkopKACilp\r\nKYBS0UlIBaSiimAmav2d0Ym2k8GqBpucUiJanVvFDdJhh9DWPcWMkPzL8y0+yusf\r\nITW2jhhzQZKTicnSVs3lvEznb8rdaynjZOvT1oubqVxlFJS0FCUtFFABSUtFMApK\r\nWikAlFFLQIKKKWgBKKKKACikooAKXFJS5oASlFFFABRmj2ooAWjgUlFABQOKKKAF\r\nopKWgBMUdKKKADNFFLQACiiigA60neiloASiiloASlpKKAFOaKKSgAooooAKKKWg\r\nAooooASiiigApaKKAEopaSgApaSigBeKKQ0vNABRSYooAWjiiigAopKWgAooAooA\r\n/9PDooooOoKKKKACiikoAWiiikMKKKKYBRRRQAtJRRSAWkopaYBRRRSASlopKACi\r\nlNFMBMVGRUlIRSJaGKxRsitu3uCQCTxWGRTo32nHagxaOhuJUkAA6jvVfIYYaoUk\r\nDAA0/kVLGiF7cHmP8qqkFTgitTypcfdNXxDFPEPMTBx9DTRfPY5ulq1dWrW7ZHKn\r\noaqUzVO4UUUUAFH1oooAMUYpaKAEooooEFFFFABSUtFACUUtJTAWikpaQBRRRQAU\r\nUUGgAFHailzQAmKKU0lABSUtJQAtLSUUAKaSiigAxRRRQAe9FFFABRxQKKACiijN\r\nABS0lHegApaSigBfakpaKAEpTSUUAGaM0c96WgAopKWgAooxSUALzRRmkoAWiiig\r\nAooooASiiigBaMUUUAf/1MOiiig6gooopDCiiigAooopgFFFFIAooopgFFFLQAlL\r\nSUtIAooooAKKSlpgFFFFIApMUtFADCKjNTGoyKDOSJoZCD64rRRldlb3GRWMCVOR\r\nV+OToy0GZ0maUHsapwXAkGD1q1QSK6K6lWGQawbq0aA7l5Q963wexoZQwKsMg0FR\r\nlY5Skq/d2hhO9OU/lVGg6U7iUUUtACUtJRQAUUUUAFFFFAhKWiigBaSikoAWij3o\r\noAKKKWgBKKKM0AFFFHWgAoopaAEoo60UAFFFFABS0c0negBaKKSgBaSiigAo+tFF\r\nAC0lFFABRS0lABR0oooAXrRSUUALSUtFACUUUUALRSUtACdaKWigBKWj60UAFFFF\r\nABS02loAKKKKACijFIfagD//1cOiiig6gooopAFFFFMAooo6UDCiiikAUUUUwCii\r\nigAoopaQBRSUtMAooopAFFFFMAoooNIBDTTT6Q0CZERSo5Q5FKRTSKDJovRyfxoa\r\n17e4Egwetc0rFDkVdSTPzJ1oIaOkpwPrVC3uQ42t1q7QIcQCMHkGsS8szETJHyv8\r\n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\r\nS0lFAC0UUUgCikpaAEpaBRQAUUUtACUUUUALRSUUAFLSUtABRRRQAUlLRQA3FJin\r\nUUxNEZFNIqQimkUiGgRyn0q5FIU+cHiqJFORyhoIOlhnEgwetWa51JGBBjPFbEE4\r\nkGD1oJaLoPY0jKGBVhkGm08HsaBGFd2jQ/OnKH9KpV1TKCMHkGsS7szF+8j5X+VB\r\nvCd9GUKTNFFBoFFFFABRRRQAUUlFABRRRQAoFJRRQIKKKKACgUUUAFFFFAC0lFHS\r\ngApeKPrSUAFFFFABRS0UAJRSmkoAKKKKACiiigAooooAKKKWgBKWiigBKKWigD//\r\n18OikpaR1BRRRTASloooAKKKKQwpKWigQUUUUwCiiikMKKKKAClpKKYBRS0lAC0U\r\nUUAJRRRQAUUUtIApKKKAFooooAKKKXNACUUtJQAUtFJTAKWkopALRRRQMKKKSgBa\r\nKKKAExTafSUEjMUwipaaRQS0IjsjZFXIXxytUSKcjshyKCGjo4Jw42t1q2K59JAw\r\n3r1FalvcBxg0EWLwNKRx7UwU4GgRj3dnszLF93uPSs2urIrIu7LbmWIcdxQbQn0Z\r\nl0UUtBsJRRRQIKSlooASiiigAooooEFFFFABRRRQAUUUUAFLSUUALSdKKKACiiig\r\nAoopc0AFFFFACUUUUAFFFLQAlFFFABS8UlLQAUGiigApOtFFAH//0MOikpaDqCii\r\nigAopKWgAooooGFFFFIAooopiCiiigYUUUUgClpKKACiiimAUUUUAFFFFABS0UUg\r\nEopaKYBRRRSAKKKKACilpKACiiigBaKKKADNFJS0DCiiigAooooAKSlooASkNPpt\r\nAhhFNIqTFNIoIaGqxQ5FWVcj54/xFViKAxU5FBB0NtchwAavVzkcg25Qc9xWpb3I\r\nYAGkQ0aIOKd9KjBpwOKYjLu7LrLCPqKyq6qsy7s92ZYhz3FBtCfRmPRS+1JQbBRR\r\nRQIKSlzRQAUlFFAC0UUUCEopc0lABRRiigAopaSgAooooAKKKKACiiigBaM0Un1o\r\nAWkoooAXvSdKKKACiiigAopaSgBc0lLRQAGk4paKAP/RwqWkooOoWkoooAKWkooA\r\nWiiigAooopAFFFFABRRRQMKKKKACiiigAopaSmAUtJRQAUUUtIBKWiigAooooAKK\r\n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\r\nFFAooASilooASlpKKAFopKKAClpKKAP/08Kiiig6gooooAKKKKACiiigAooooAKW\r\nkpaAEopaSgApaKKACiikoAWiiigAooopAFFFFMAooooGFFLSUhBRRRTGFFFFABRR\r\nRQAUUUUhBRRRTAKKKSgApKWigQlJTqSgQ2ilooEJRS0UBYSilooCwlJTqKBWG4op\r\n1FILCUtFLQNIUU6kooLQtLSUUDFpKKKAFopKKAClpKWgBM0UtJQAUlLRQA3FNp9J\r\nTJaIyKTkHIqTFNIpENE8bhjz1FaVvclDtfpWJyOlWY5N3B60EM6ZSCMinA1kW9wU\r\nO1ulaqsGGRQSS9apXVosw3Lw386tZp+c0DTscsyshKsMEUyuhubVZxkcMOhrCdGR\r\nirjBFB0RlcjooooKCiiigQUUUUAFJRRQAUUUUAFFFFAH/9TCooooOoKKKKACiiig\r\nAooooAKWkooAKKKKACiiigApaSigBaSlooASloooAKKKKQBRRRTAKKKKQwooopiC\r\niiigAooooGFFFFABRRRQIKKKKACiig0AJRRS0AJSUtFAhKKKKBCUUtFACUUtFABR\r\nRS0AJRS0lAwpaKWkAUtFFAwooozQMKM0UUAFFFFABRRRQAtJRRQAUUUUAFJTqDQI\r\nbTTTqSmJoYRTelSYppFIhonjkz8rdavwXBjOG6Vj1Yjkz8rUENHTKwYZFPzWLBOY\r\njg9K1kcOMrQSTA5qtc2yzr6MOhqbNJJKkaFnOKBp2ObkjaJijjBFR1aupzO4PQDp\r\nVWg6E7oWikooGFFFFABRRRQAUUUUALSUUUCP/9XCooooOoKKKKACiiigAooooAKK\r\nKKACiiigApaSloASiiigApaSloASilpKAFooopAFFJS0wCikpaQBRRRTAKKKKQBR\r\nRRQAUUUUAFFFFABRRRTAKKKKACiiigApKWigBKKWkoEFFFFABRRS0AJS0lLQMSil\r\nooAKKKWkAUUUUDCiikpgLRRRSAKKKKYBRRRSAKKKKACikpaAClpKKACkpaKAG0hp\r\n1JTJaGEU2pMU0ikQ0SxyfwtV6Gdoj7VlVLHJj5WoIaOiM6CPfmsie4Mh3Hp2FVml\r\n7CoSSx5oEkSAk806mrTqDoWwUUUUDCjFFFAC4pKKKACiiigAooooEf/WwqKKKDqF\r\npKKKACiiigAooooAKKKKACiiigAooooAKKKKAFooopAJS0UUAFFJRTAWikooAKWk\r\nooAWikpaACiiikAUUUUxhRRRSAKKKKBBRRRTGFFFFIAooooEFFFFABRRRTASlooo\r\nAKKKKACiiigAFFLRSASlpKKBi0UUUAFFFFABRRRQAUUUUAFFFFACUUtJTAWiiikA\r\nUUUUAFFFFABSUtFAhtNp9JimJoYRTakpuKRDQzFPAoxTgKASHClpKWg0CiiigAop\r\naSgYUUUUAFFFFAgoozSUDP/XwqKKKDqCiiigAooooAKKKKACiiigAoopaAEooooA\r\nKKKKAFooopAFFFJTAKKWkoAWkopaACiikoAWiiigAooooAKKKKQBRSUtMAooooGF\r\nFFFABRRRQIKWkopALSGilNACUUUUALRSUtACUtFFACUtJS0xhRSUCkAUtFJTAWkp\r\naSkAtFJRQAtJRS0wCiiikAUUUUAFJS0UAFFFJQAtFFJQAtHFFFABRRRQAlFLSUCE\r\nxSYp1JTFYSlFFLQFhaKKKRQUUUUAFFJS0AFFFJQIWiiigYUUlLQI/9DCooooOoKK\r\nKKACiiigBaSiigAooooAKKKWgBKKWigBKKKKAFpKKKAFoopKAFpKKWgBKWiigApK\r\nKWgBKWkooAWiiigAooooASloooAKKKKQwooooAKKKKYgooooAKWkopAFFFFMYUUU\r\nUCFpKKKBhRRRSAKWkopgLSUUUgClpKKAFooooAKSlooEFFJS0xhRRRSASlpKKAFp\r\nKWkoAWiiigQlFLRTGFFFJQAtFFBpCCkpaKYxMUtFFIQUUUlAwpaSloASloooAKSl\r\nooEFJS0lAC0UUlAz/9HCooooOoKKKKACiiigAooooAKKKKACiiigBaKSigBaSiig\r\nAopaSgAooooAKWkpaACkpaSgAooooAKKKKACloooAKKKSgBaKSlpAFFFFMAooooG\r\nFFFFAgooopAFFFFABRRRTAKKKWkAlFFFMYUUUUhC0lFFMApaSigYUUUtIBKWkpaB\r\nBSUtFMYlLRSUgClopKBBRS0UAFFFJQAUtJS0AFFFFAwopKKAClpKWgAopKWgAooN\r\nJQAUtJRQIWkopaAEopaSgBaKSigBaKKKAEpaSimM/9LCooooOoKKKKACiiigAooo\r\noAKKWkoAKKKWgBKKKKACiiigAooooAKKWkoAKKKWgApKKKACilpKAClpKWgBKWkp\r\naACkpaSgApaSlpAFFFFMAooooAKKKKQBRRRTGFFFFAgooopAFFFFABRRRTAKKKKA\r\nCiiigAooooAKKKKBhRRRQIKKKKBhRRRQAUUUUAFLSUUhC0UlLQMKKKSgQtFJRQMK\r\nKKKACiilpiEopaSgBaSlpKACiiigYUUUUhBS0lLmgYlLSUtAgpKWkoGFFLRTA//T\r\nwqKKKDqCiiigAooooAKKKKACiiigAooooAKKKWgBKKKKACiiigAooooAKKKKACii\r\nigAooooAKKKKACiiigBaKKSkAUtFFMAooooAKKKKQBRRRTAKKKKACiiigAooopAF\r\nFFFABRRRTAKKKKACiiigAooooAKKKKAClpKWkAlFLSUxi0lFLSASiiigQUUUUAFL\r\nSUUAFFFFABRRS0DEpaSigQtFFFACUUUUALSUUUDCloooEJS0UlABRRRQAUUUUwFo\r\npKWkMKSiigR//9TCooooOoKKKKQBRRRTAKKKKACiiigApaKKAEooooAKKKWkAlFL\r\nSUwCiiigAooFLQAlLSUtACUUUUAFFFFABRS0UAJRS0lABS0lLSAKKWkoAKKSigBa\r\nKSlpgFFFHakAUUUUwCiijtQAUUUUAFFFLSASiiigAooopgFFFFAB1ooFBpDCiiig\r\nQUtJRQMKKKBQIKKKWgBKKKKYBS0lLSASilpKACiiigBaKSloGFFFFMQUlLR2pAFJ\r\nS0lABRRRTAWikopALSUUUALSUtFMYUUgopAFFFFAH//Z","id_addr":"","huji_addr":"","huji_code":"411002011001","cardStatus":1,"cardNo":"4127277186835","certId":"412727199311020429","realId":132,"realName":"高雅","real":1,"certMessage":"通过审核","greenStatus":"1","payPwdStatus": "0"}}
     */

    private int flag;
    private String code;
    private String desc;
    private ContentBean content;

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public ContentBean getContent() {
        return content;
    }

    public void setContent(ContentBean content) {
        this.content = content;
    }

    public static class ContentBean implements Serializable{
        /**
         * data : {"username":"15993651314","nickname":"高雅9","gender":"女","birthday":"1993-11-02","mobile":"15993651314","id":374,"headImg":"/9j/4AAQSkZJRgABAQAASABIAAD/4QBYRXhpZgAATU0AKgAAAAgAAgESAAMAAAAB\r\nAAEAAIdpAAQAAAABAAAAJgAAAAAAA6ABAAMAAAABAAEAAKACAAQAAAABAAAC7qAD\r\nAAQAAAABAAAC2AAAAADlABRRRTAWikopALSUUUALSUtFMYUUgopAFFFFAH//Z","id_addr":"","huji_addr":"","huji_code":"411002011001","cardStatus":1,"cardNo":"4127277186835","certId":"412727199311020429","realId":132,"realName":"高雅","real":1,"certMessage":"通过审核","greenStatus":"1"}
         */

        private DataBean data;

        public DataBean getData() {
            return data;
        }

        public void setData(DataBean data) {
            this.data = data;
        }

        public static class DataBean implements Serializable{
            /**
             * username : 15993651314
             * nickname : 高雅9
             * gender : 女
             * birthday : 1993-11-02
             * mobile : 15993651314
             * id : 374
             * headImg : /9j/4AAQSkZJRgABAQAASABIAAD/4QBYRXhpZgAATU0AKgAAAAgAAgESAAMAAAAB
             AAEAAIdpAAQAAAABAAAAJgAAAAAAA6ABAAMAAAABAAEAAKACAAQAAAABAAAC7qAD
             S0lABRRRTAWikopALSUUUALSUtFMYUUgopAFFFFAH//Z
             * id_addr :
             * huji_addr :
             * huji_code : 411002011001
             * cardStatus : 1
             * cardNo : 4127277186835
             * certId : 412727199311020429
             * realId : 132
             * realName : 高雅
             * real : 1
             * certMessage : 通过审核
             * greenStatus : 1
             */

            private String username;
            private String nickname;
            private String gender;
            private String birthday;
            private String mobile;
            private int id;
            private String headImg;
            private String id_addr;
            private String huji_addr;
            private String huji_code;
            private int cardStatus;
            private String cardNo;
            private String certId;
            private int realId;
            private String realName;
            private int real;
            private String certMessage;
            private String greenStatus;
            private String payPwdStatus;


            public String getPayPwdStatus() {
                return payPwdStatus;
            }

            public void setPayPwdStatus(String payPwdStatus) {
                this.payPwdStatus = payPwdStatus;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getGender() {
                return gender;
            }

            public void setGender(String gender) {
                this.gender = gender;
            }

            public String getBirthday() {
                return birthday;
            }

            public void setBirthday(String birthday) {
                this.birthday = birthday;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getHeadImg() {
                return headImg;
            }

            public void setHeadImg(String headImg) {
                this.headImg = headImg;
            }

            public String getId_addr() {
                return id_addr;
            }

            public void setId_addr(String id_addr) {
                this.id_addr = id_addr;
            }

            public String getHuji_addr() {
                return huji_addr;
            }

            public void setHuji_addr(String huji_addr) {
                this.huji_addr = huji_addr;
            }

            public String getHuji_code() {
                return huji_code;
            }

            public void setHuji_code(String huji_code) {
                this.huji_code = huji_code;
            }

            public int getCardStatus() {
                return cardStatus;
            }

            public void setCardStatus(int cardStatus) {
                this.cardStatus = cardStatus;
            }

            public String getCardNo() {
                return cardNo;
            }

            public void setCardNo(String cardNo) {
                this.cardNo = cardNo;
            }

            public String getCertId() {
                return certId;
            }

            public void setCertId(String certId) {
                this.certId = certId;
            }

            public int getRealId() {
                return realId;
            }

            public void setRealId(int realId) {
                this.realId = realId;
            }

            public String getRealName() {
                return realName;
            }

            public void setRealName(String realName) {
                this.realName = realName;
            }

            public int getReal() {
                return real;
            }

            public void setReal(int real) {
                this.real = real;
            }

            public String getCertMessage() {
                return certMessage;
            }

            public void setCertMessage(String certMessage) {
                this.certMessage = certMessage;
            }

            public String getGreenStatus() {
                return greenStatus;
            }

            public void setGreenStatus(String greenStatus) {
                this.greenStatus = greenStatus;
            }
        }
    }
}
