package net.itarray.automotion.tests.errors;

import net.itarray.automotion.internal.Errors;
import net.itarray.automotion.internal.ResponsiveUIValidatorBase;
import net.itarray.automotion.validation.ChunkUIElementValidator;
import net.itarray.automotion.validation.ResponsiveUIValidator;
import net.itarray.automotion.validation.UIElementValidator;
import net.itarray.automotion.validation.UISnapshot;
import net.itarray.automotion.validation.properties.Padding;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import rectangles.DummyDriverFacade;
import rectangles.DummyWebElement;

import java.util.Arrays;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static rectangles.DummyWebElement.createElement;

public class ErrorMessagesRegressionTest {

    private DummyWebElement element;
    private String elementName;
    private ResponsiveUIValidatorBase base;

    @Before
    public void setUp() {
        element = (DummyWebElement) createElement(100, 200, 500, 400);
        elementName = "under test";
    }

    public UIElementValidator createElementValidator() {
        DummyDriverFacade driverFacade = new DummyDriverFacade();
        driverFacade.setPageSize(new Dimension(2000, 1000));
        ResponsiveUIValidator uiValidator = new ResponsiveUIValidator(driverFacade);
        UISnapshot snapshot = uiValidator.snapshot();
        UIElementValidator result = snapshot.findElement(this.element, elementName);
        base = (ResponsiveUIValidatorBase) result;
        return result;
    }

    public ChunkUIElementValidator createChunkValidator(WebElement other) {
        DummyDriverFacade driverFacade = new DummyDriverFacade();
        ResponsiveUIValidator uiValidator = new ResponsiveUIValidator(driverFacade);
        driverFacade.setPageSize(new Dimension(2000, 1000));
        UISnapshot snapshot = uiValidator.snapshot();
        ChunkUIElementValidator result = snapshot.findElements(Arrays.asList(element, other));
        base = (ResponsiveUIValidatorBase) result;
        return result;
    }

    public ChunkUIElementValidator createChunkValidator() {
        DummyDriverFacade driverFacade = new DummyDriverFacade();
        ResponsiveUIValidator uiValidator = new ResponsiveUIValidator(driverFacade);
        driverFacade.setPageSize(new Dimension(2000, 1000));
        UISnapshot snapshot = uiValidator.snapshot();
        ChunkUIElementValidator result = snapshot.findElements(Arrays.asList(element));
        base = (ResponsiveUIValidatorBase) result;
        return result;
    }

    @Test
    public void isVerticallyLeftAlignedWith() {
        createElementValidator().isVerticallyLeftAlignedWith(createElement(105, 200, 500, 400), "specifying");
        Errors errors = base.getErrors();
        assertThat(errors.getLastMessage())
                .isEqualTo("Element 'under test' has not the same left offset as element 'specifying'");
    }

    @Test
    public void isVerticallyLeftAlignedWithList() {
        createElementValidator().isVerticallyLeftAlignedWith(singletonList(createElement(105, 200, 500, 400)));
        Errors errors = base.getErrors();
        assertThat(errors.getLastMessage())
                .isEqualTo("Element 'under test' has not the same left offset as element 'with properties: tag=[null], id=[null], class=[null], text=[], coord=[105,200], size=[395,200]'");
    }

    @Test
    public void sameOffsetLeftAsWithChunk() {
        createChunkValidator(createElement(105, 200, 500, 400)).sameLeftOffset();
        Errors errors = base.getErrors();
        assertThat(errors.getLastMessage())
                .isEqualTo("Element #1 has not the same left offset as element #2");
    }

    @Test
    public void isVerticallyRightAlignedWith() {
        createElementValidator().isVerticallyRightAlignedWith(createElement(100, 200, 505, 400), "specifying");
        Errors errors = base.getErrors();
        assertThat(errors.getLastMessage())
                .isEqualTo("Element 'under test' has not the same right offset as element 'specifying'");
    }

    @Test
    public void isVerticallyRightAlignedWithList() {
        createElementValidator().isVerticallyRightAlignedWith(singletonList(createElement(100, 200, 505, 400)));
        Errors errors = base.getErrors();
        assertThat(errors.getLastMessage())
                .isEqualTo("Element 'under test' has not the same right offset as element 'with properties: tag=[null], id=[null], class=[null], text=[], coord=[100,200], size=[405,200]'");
    }

    @Test
    public void sameOffsetRightAsWithChunk() {
        createChunkValidator(createElement(100, 200, 505, 400)).sameRightOffset();
        Errors errors = base.getErrors();
        assertThat(errors.getLastMessage())
                .isEqualTo("Element #1 has not the same right offset as element #2");
    }

    @Test
    public void isHorizontallyTopAlignedWith() {
        createElementValidator().isHorizontallyTopAlignedWith(createElement(100, 205, 500, 400), "specifying");
        Errors errors = base.getErrors();
        assertThat(errors.getLastMessage())
                .isEqualTo("Element 'under test' has not the same top offset as element 'specifying'");
    }

    @Test
    public void isHorizontallyTopAlignedWithList() {
        createElementValidator().isHorizontallyTopAlignedWith(singletonList(createElement(100, 205, 500, 400)));
        Errors errors = base.getErrors();
        assertThat(errors.getLastMessage())
                .isEqualTo("Element 'under test' has not the same top offset as element 'with properties: tag=[null], id=[null], class=[null], text=[], coord=[100,205], size=[400,195]'");
    }

    @Test
    public void sameOffsetTopAsWithChunk() {
        createChunkValidator(createElement(100, 205, 500, 400)).sameTopOffset();
        Errors errors = base.getErrors();
        assertThat(errors.getLastMessage())
                .isEqualTo("Element #1 has not the same top offset as element #2");
    }

    @Test
    public void isHorizontallyBottomAlignedWith() {
        createElementValidator().isHorizontallyBottomAlignedWith(createElement(100, 200, 500, 405), "specifying");
        Errors errors = base.getErrors();
        assertThat(errors.getLastMessage())
                .isEqualTo("Element 'under test' has not the same bottom offset as element 'specifying'");
    }

    @Test
    public void isHorizontallyBottomAlignedWithList() {
        createElementValidator().isHorizontallyBottomAlignedWith(singletonList(createElement(100, 200, 500, 405)));
        Errors errors = base.getErrors();
        assertThat(errors.getLastMessage())
                .isEqualTo("Element 'under test' has not the same bottom offset as element 'with properties: tag=[null], id=[null], class=[null], text=[], coord=[100,200], size=[400,205]'");
    }

    @Test
    public void sameOffsetBottomAsWithChunk() {
        createChunkValidator(createElement(100, 200, 500, 405)).sameBottomOffset();
        Errors errors = base.getErrors();
        assertThat(errors.getLastMessage())
                .isEqualTo("Element #1 has not the same bottom offset as element #2");
    }

    @Test
    public void isRightOfElement() {
        createElementValidator().isRightOf(createElement(105, 205, 505, 405));
        Errors errors = base.getErrors();
        assertThat(errors.getLastMessage())
                .isEqualTo("Left element aligned not properly");
    }

    @Test
    public void isRightOfElementAndRange() {
        createElementValidator().isRightOf(createElement(105, 205, 505, 405), 4, 6);
        Errors errors = base.getErrors();
        assertThat(errors.getLastMessage())
                .isEqualTo("Left element aligned not properly. Expected margin should be between 4px and 6px. Actual margin is -405px");
    }

    @Test
    public void isLeftOfElement() {
        createElementValidator().isLeftOf(createElement(105, 205, 505, 405));
        Errors errors = base.getErrors();
        assertThat(errors.getLastMessage())
                .isEqualTo("Right element aligned not properly");
    }

    @Test
    public void isLeftOfElementAndRange() {
        createElementValidator().isLeftOf(createElement(105, 205, 505, 405), 4, 6);
        Errors errors = base.getErrors();
        assertThat(errors.getLastMessage())
                .isEqualTo("Right element aligned not properly. Expected margin should be between 4px and 6px. Actual margin is -395px");
    }

    @Test
    public void isBelowElement() {
        createElementValidator().isBelow(createElement(105, 205, 505, 405));
        Errors errors = base.getErrors();
        assertThat(errors.getLastMessage())
                .isEqualTo("Above element aligned not properly");
    }

    @Test
    public void isBelowElementAndMargin() {
        createElementValidator().isBelow(createElement(105, 205, 505, 405), 4, 6);
        Errors errors = base.getErrors();
        assertThat(errors.getLastMessage())
                .isEqualTo("Above element aligned not properly. Expected margin should be between 4px and 6px. Actual margin is -205px");
    }

    @Test
    public void isAboveElement() {
        createElementValidator().isAbove(createElement(105, 205, 505, 405));
        Errors errors = base.getErrors();
        assertThat(errors.getLastMessage())
                .isEqualTo("Below element aligned not properly");
    }

    @Test
    public void isAboveElementAndMargin() {
        createElementValidator().isAbove(createElement(105, 205, 505, 405), 4, 6);
        Errors errors = base.getErrors();
        assertThat(errors.getLastMessage())
                .isEqualTo("Below element aligned not properly. Expected margin should be between 4px and 6px. Actual margin is -195px");
    }

    @Test
    public void sameWidthAs() {
        createElementValidator().sameWidthAs(createElement(100, 200, 505, 405), "specifying");
        Errors errors = base.getErrors();
        assertThat(errors.getLastMessage())
                .isEqualTo("Element 'under test' has not the same width as element 'specifying'. Width of 'under test' is 400px. Width of element is 405px");
    }

    @Test
    public void sameWidthAsWithList() {
        createElementValidator().sameWidthAs(singletonList(createElement(100, 200, 505, 405)));
        Errors errors = base.getErrors();
        assertThat(errors.getLastMessage())
                .isEqualTo("Element 'under test' has not the same width as element 'with properties: tag=[null], id=[null], class=[null], text=[], coord=[100,200], size=[405,205]'. Width of 'under test' is 400px. Width of element is 405px");
    }

    @Test
    public void sameHeightAs() {
        createElementValidator().sameHeightAs(createElement(100, 200, 505, 405), "specifying");
        Errors errors = base.getErrors();
        assertThat(errors.getLastMessage())
                .isEqualTo("Element 'under test' has not the same height as element 'specifying'. Height of 'under test' is 200px. Height of element is 205px");
    }

    @Test
    public void sameHeightAsWithList() {
        createElementValidator().sameHeightAs(singletonList(createElement(100, 200, 505, 405)));
        Errors errors = base.getErrors();
        assertThat(errors.getLastMessage())
                .isEqualTo("Element 'under test' has not the same height as element 'with properties: tag=[null], id=[null], class=[null], text=[], coord=[100,200], size=[405,205]'. Height of 'under test' is 200px. Height of element is 205px");
    }

    @Test
    public void sameSizeAs() {
        createElementValidator().sameSizeAs(createElement(100, 200, 505, 405), "specifying");
        Errors errors = base.getErrors();
        assertThat(errors.getLastMessage())
                .isEqualTo("Element 'under test' has not the same size as element 'specifying'. Size of 'under test' is 400px x 200px. Size of element is 405px x 205px");
    }

    @Test
    public void sameSizeAsWithList() {
        createElementValidator().sameSizeAs(singletonList(createElement(100, 200, 505, 405)));
        Errors errors = base.getErrors();
        assertThat(errors.getLastMessage())
                .isEqualTo("Element 'under test' has not the same size as element 'with properties: tag=[null], id=[null], class=[null], text=[], coord=[100,200], size=[405,205]'. Size of 'under test' is 400px x 200px. Size of element is 405px x 205px");
    }

    @Test
    public void notSameSizeAs() {
        createElementValidator().notSameSizeAs(createElement(100, 200, 500, 400), "specifying");
        Errors errors = base.getErrors();
        assertThat(errors.getLastMessage())
                .isEqualTo("Element 'under test' has the same size as element 'specifying'. Size of 'under test' is 400px x 200px. Size of element is 400px x 200px");
    }

    @Test
    public void notSameSizeAsWithList() {
        createElementValidator().notSameSizeAs(singletonList(createElement(100, 200, 500, 400)));
        Errors errors = base.getErrors();
        assertThat(errors.getLastMessage())
                .isEqualTo("Element 'under test' has the same size as element 'with properties: tag=[null], id=[null], class=[null], text=[], coord=[100,200], size=[400,200]'. Size of 'under test' is 400px x 200px. Size of element is 400px x 200px");
    }

    @Test
    public void notOverlapWith() {
        createElementValidator().notOverlapWith(createElement(100, 200, 500, 400), "specifying");
        Errors errors = base.getErrors();
        assertThat(errors.getLastMessage())
                .isEqualTo("Element 'under test' is overlapped with element 'specifying' but should not");
    }

    @Test
    public void notOverlapWithWithList() {
        createElementValidator().notOverlapWith(singletonList(createElement(100, 200, 500, 400)));
        Errors errors = base.getErrors();
        assertThat(errors.getLastMessage())
                .isEqualTo("Element 'under test' is overlapped with element 'with properties: tag=[null], id=[null], class=[null], text=[], coord=[100,200], size=[400,200]' but should not");
    }

    @Test
    public void overlapWith() {
        createElementValidator().overlapWith(createElement(1100, 1200, 500, 400), "specifying");
        Errors errors = base.getErrors();
        assertThat(errors.getLastMessage())
                .isEqualTo("Element 'under test' is not overlapped with element 'specifying' but should be");
    }

    @Test
    public void insideOf() {
        createElementValidator().insideOf(createElement(1100, 1200, 500, 400), "specifying");
        Errors errors = base.getErrors();
        assertThat(errors.getLastMessage())
                .isEqualTo("Element 'under test' is not inside of 'specifying'");
    }

    @Test
    public void insideOfChunk() {
        createChunkValidator().insideOf(createElement(1100, 1200, 500, 400), "specifying");
        Errors errors = base.getErrors();
        assertThat(errors.getLastMessage())
                .isEqualTo("Element 'with properties: tag=[null], id=[null], class=[null], text=[], coord=[100,200], size=[400,200]' is not inside of 'specifying'");
    }

    @Test
    public void insideOfWithPadding() {
        Padding padding = new Padding(5, 6, 7, 8);
        createElementValidator().insideOf(createElement(1100, 1200, 500, 400), "specifying", padding);
        Errors errors = base.getErrors();
        assertThat(errors.getLastMessage())
                .isEqualTo("Padding of element 'under test' is incorrect. Expected padding: top[5], right[6], bottom[7], left[8]. Actual padding: top[-1000], right[0], bottom[0], left[-1000]");
    }

    @Test
    public void maxOffsetTop() {
        createElementValidator().maxOffset(200-10,1500,600,100);
        Errors errors = base.getErrors();
        assertThat(errors.getLastMessage())
                .isEqualTo("Expected max top offset of element 'under test' is: 190px. Actual top offset is: 200px");
    }

    @Test
    public void maxOffsetRight() {
        createElementValidator().maxOffset(200,1500-10,600,100);
        Errors errors = base.getErrors();
        assertThat(errors.getLastMessage())
                .isEqualTo("Expected max right offset of element 'under test' is: 1490px. Actual right offset is: 1500px");
    }

    @Test
    public void maxOffsetBottom() {
        createElementValidator().maxOffset(200,1500,600-10,100);
        Errors errors = base.getErrors();
        assertThat(errors.getLastMessage())
                .isEqualTo("Expected max bottom offset of element 'under test' is: 590px. Actual bottom offset is: 600px");
    }

    @Test
    public void maxOffsetLeft() {
        createElementValidator().maxOffset(200,1500,600,100-10);
        Errors errors = base.getErrors();
        assertThat(errors.getLastMessage())
                .isEqualTo("Expected max left offset of element 'under test' is: 90px. Actual left offset is: 100px");
    }

    @Test
    public void minOffsetTop() {
        createElementValidator().minOffset(200+10,1500,600,100);
        Errors errors = base.getErrors();
        assertThat(errors.getLastMessage())
                .isEqualTo("Expected min top offset of element 'under test' is: 210px. Actual top offset is: 200px");
    }

    @Test
    public void minOffsetRight() {
        createElementValidator().minOffset(200,1500+10,600,100);
        Errors errors = base.getErrors();
        assertThat(errors.getLastMessage())
                .isEqualTo("Expected min right offset of element 'under test' is: 1510px. Actual right offset is: 1500px");
    }

    @Test
    public void minOffsetBottom() {
        createElementValidator().minOffset(200,1500,600+10,100);
        Errors errors = base.getErrors();
        assertThat(errors.getLastMessage())
                .isEqualTo("Expected min bottom offset of element 'under test' is: 610px. Actual bottom offset is: 600px");
    }

    @Test
    public void minOffsetLeft() {
        createElementValidator().minOffset(200,1500,600,100+10);
        Errors errors = base.getErrors();
        assertThat(errors.getLastMessage())
                .isEqualTo("Expected min left offset of element 'under test' is: 110px. Actual left offset is: 100px");
    }

    @Test
    public void equalLeftRightOffset() {
        createElementValidator().equalLeftRightOffset();
        Errors errors = base.getErrors();
        assertThat(errors.getLastMessage())
                .isEqualTo("Element 'under test' has not equal left and right offset. Left offset is 100px, right is 1500px");
    }

    @Test
    public void equalLeftRightOffsetChunk() {
        createChunkValidator().equalLeftRightOffset();
        Errors errors = base.getErrors();
        assertThat(errors.getLastMessage())
                .isEqualTo("Element 'with properties: tag=[null], id=[null], class=[null], text=[], coord=[100,200], size=[400,200]' has not equal left and right offset. Left offset is 100px, right is 1500px");
    }

    @Test
    public void equalTopBottomOffset() {
        createElementValidator().equalTopBottomOffset();
        Errors errors = base.getErrors();
        assertThat(errors.getLastMessage())
                .isEqualTo("Element 'under test' has not equal top and bottom offset. Top offset is 200px, bottom is 600px");
    }

    @Test
    public void equalTopBottomOffsetChunk() {
        createChunkValidator().equalTopBottomOffset();
        Errors errors = base.getErrors();
        assertThat(errors.getLastMessage())
                .isEqualTo("Element 'with properties: tag=[null], id=[null], class=[null], text=[], coord=[100,200], size=[400,200]' has not equal top and bottom offset. Top offset is 200px, bottom is 600px");
    }

    @Test
    public void minWidth() {
        createElementValidator().minWidth(1000);
        Errors errors = base.getErrors();
        assertThat(errors.getLastMessage())
                .isEqualTo("Expected min width of element 'under test' is: 1000px. Actual width is: 400px");
    }

    @Test
    public void maxWidth() {
        createElementValidator().maxWidth(10);
        Errors errors = base.getErrors();
        assertThat(errors.getLastMessage())
                .isEqualTo("Expected max width of element 'under test' is: 10px. Actual width is: 400px");
    }

    @Test
    public void widthBetweenUpper() {
        createElementValidator().widthBetween(1000, 2000);
        Errors errors = base.getErrors();
        assertThat(errors.getLastMessage())
                .isEqualTo("Expected min width of element 'under test' is: 1000px. Actual width is: 400px");
    }

    @Test
    public void widthBetweenLower() {
        createElementValidator().widthBetween(10, 20);
        Errors errors = base.getErrors();
        assertThat(errors.getLastMessage())
                .isEqualTo("Expected max width of element 'under test' is: 20px. Actual width is: 400px");
    }

    @Test
    public void minHeight() {
        createElementValidator().minHeight(1000);
        Errors errors = base.getErrors();
        assertThat(errors.getLastMessage())
                .isEqualTo("Expected min height of element 'under test' is: 1000px. Actual height is: 200px");
    }

    @Test
    public void maxHeight() {
        createElementValidator().maxHeight(10);
        Errors errors = base.getErrors();
        assertThat(errors.getLastMessage())
                .isEqualTo("Expected max height of element 'under test' is: 10px. Actual height is: 200px");
    }

    @Test
    public void heightBetweenUpper() {
        createElementValidator().heightBetween(1000, 2000);
        Errors errors = base.getErrors();
        assertThat(errors.getLastMessage())
                .isEqualTo("Expected min height of element 'under test' is: 1000px. Actual height is: 200px");
    }

    @Test
    public void heightBetweenLower() {
        createElementValidator().heightBetween(10, 20);
        Errors errors = base.getErrors();
        assertThat(errors.getLastMessage())
                .isEqualTo("Expected max height of element 'under test' is: 20px. Actual height is: 200px");
    }

    @Test
    public void hasCssValueNoValue() {
        createElementValidator().hasCssValue("font-size", "12px");
        Errors errors = base.getErrors();
        assertThat(errors.getLastMessage())
                .isEqualTo("Element 'under test' does not have css property 'font-size'");
    }

    @Test
    public void hasCssValueDifferentValue() {
        element.putCssValue("font-size", "16px");
        createElementValidator().hasCssValue("font-size", "12px");
        Errors errors = base.getErrors();
        assertThat(errors.getLastMessage())
                .isEqualTo("Expected value of 'font-size' is '12px'. Actual value is '16px'");
    }

    @Test
    public void doesNotHaveCssValueNoValue() {
        createElementValidator().doesNotHaveCssValue("font-size", "12px");
        Errors errors = base.getErrors();
        assertThat(errors.getLastMessage())
                .isEqualTo("Element 'under test' does not have css property 'font-size'");
    }


    @Test
    public void doesNotHaveCssValueDifferentValue() {
        element.putCssValue("font-size", "12px");
        createElementValidator().doesNotHaveCssValue("font-size", "12px");
        Errors errors = base.getErrors();
        assertThat(errors.getLastMessage())
                .isEqualTo("CSS property 'font-size' should not contain value '12px'. Actual value is '12px'");
    }

}
